package com.example.foodapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.User
import com.example.foodapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentProfile : Fragment(R.layout.fragment_profile) {
    companion object {
        const val REQIMG = 1
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseStorage: StorageReference
    private lateinit var firebaseDatabase: DatabaseReference
    private var imageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        firebaseStorage = FirebaseStorage.getInstance().getReference("user")
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("user")

        binding.editProfileButton.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, REQIMG)
            }
        }

        binding.submitButton.setOnClickListener {
            saveImage()
        }

        var uid=FirebaseAuth.getInstance().currentUser?.uid
        uid?.let {
            firebaseDatabase.child(uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var user=snapshot.getValue(User::class.java)
                    if (user!=null){
                        Glide.with(requireContext())
                            .load(user.image)
                            .into(binding.profileImage)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    private fun saveImage() {
        imageUri?.let {
            val uid = FirebaseAuth.getInstance().currentUser?.uid
            val filename = "image $uid"
            firebaseStorage.child(filename).putFile(imageUri!!).addOnSuccessListener { uploadTaskSnapshot ->
                uploadTaskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                    val user = hashMapOf<String, Any>()
                    user["image"]=uri.toString()
                    uid?.let {
                        firebaseDatabase.child(it).updateChildren(user).addOnSuccessListener {
                            Toast.makeText(requireContext(), "Sukses update foto profil", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQIMG && resultCode == AppCompatActivity.RESULT_OK) {
            data?.data?.let { uri ->
                imageUri = uri
                binding.profileImage.setImageURI(uri)
            }
        }
    }
}
