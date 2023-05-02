package com.example.homework7_android2.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework7_android2.adapters.ChatAdapter
import com.example.homework7_android2.databinding.FragmentChatBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val db = Firebase.firestore
    private lateinit var chatAdapter: ChatAdapter
    private var list = ArrayList<String>()
    val collectionRef = db.collection("user")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        initialization()
        setUpListener()
    }

    private fun setData() {
        collectionRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    list.add(document.data["name"] as String)
                }
                chatAdapter.setList(list)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    private fun initialization() {
        chatAdapter = ChatAdapter(list)
        binding.chatRecyclerView.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setUpListener() {
        binding.buttonSendMassage.setOnClickListener {
            val text = binding.editTextInputText.text.toString()
            val user = hashMapOf(
                "name" to text
            )
            db.collection("user").add(user).addOnSuccessListener { }
            list.add(text)
            chatAdapter.setList(list)
            binding.editTextInputText.text.clear()
        }
    }
}
