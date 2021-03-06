package com.juanca.reto1.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.juanca.reto1.R
import com.juanca.reto1.databinding.ActivityMainBinding
import com.juanca.reto1.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigator: BottomNavigationView

    private lateinit var homeFragment: HomeFragment
    private lateinit var newPostFragment: NewPostFragment
    private lateinit var profileFragment: ProfileFragment

    private var loggedUser = LoginActivity.loggedUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeFragment = HomeFragment.newInstance()
        newPostFragment = NewPostFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()

        newPostFragment.newPostListener = homeFragment

        showFragment(homeFragment)

        navigator = binding.menuNav
        navigator.setOnItemSelectedListener {menuItem->
            if(menuItem.itemId == R.id.itemHome){
                showFragment(homeFragment)
            }else if(menuItem.itemId == R.id.itemPublish){
                showFragment(newPostFragment)
            }else if(menuItem.itemId == R.id.itemProfile){
                showFragment(profileFragment)
            }
            true
        }
    }

    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    public fun getNavigator(): BottomNavigationView{
        return navigator
    }

}