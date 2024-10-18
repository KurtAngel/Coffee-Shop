package com.example.coffeeshop.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.coffeeshop.Model.CategoryModel
import com.example.coffeeshop.Model.ItemsModel

class MainViewModel:ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _category =  MutableLiveData<MutableList<CategoryModel>>()
    private val _popular =  MutableLiveData<MutableList<ItemsModel>>()
    private val _offer =  MutableLiveData<MutableList<ItemsModel>>()

    val category: LiveData<MutableList<CategoryModel>> =  _category
    val popular: LiveData<MutableList<ItemsModel>> =  _popular
    val offer: LiveData<MutableList<ItemsModel>> =  _popular

    fun loadCategory() {

        val myRef = firebaseDatabase.getReference("Category")
        myRef.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<CategoryModel>()

                for (childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(CategoryModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _category.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Firebase", "Disconnected from database")
            }
        })
    }

    fun loadPopular(){
        val myRef = firebaseDatabase.getReference("Items")
        myRef.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ItemsModel>()

                for (childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _popular.value = lists
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("Firebase", "Disconnected from database")
            }
        })

    }

    fun loadOffer(){
        val myRef = firebaseDatabase.getReference("Offers")
        myRef.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ItemsModel>()

                for (childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _offer.value = lists
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("Firebase", "Disconnected from database")
            }
        })
    }
}
