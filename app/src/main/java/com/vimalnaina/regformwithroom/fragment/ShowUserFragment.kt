package com.vimalnaina.regformwithroom.fragment

import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vimalnaina.regformwithroom.R
import com.vimalnaina.regformwithroom.adapter.UserAdapter
import com.vimalnaina.regformwithroom.db.UserDatabase
import com.vimalnaina.regformwithroom.helper.SharedPreference
import kotlinx.android.synthetic.main.fragment_show_user.*
import kotlinx.coroutines.launch

class ShowUserFragment : BaseFragment() {

    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val altertDialog = AlertDialog.Builder(activity!!)
                    .setTitle("Close App")
                    .setMessage("Do you want to exit application?")
                    .setPositiveButton("Yes") {_, _ ->
                        activity!!.finish()
                    }
                    .setNegativeButton("No") {_, _ ->
                    }
                    .create()

                altertDialog.show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_user, container, false)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        getUsers()
    }

    private fun getUsers() {
        launch {
            context?.let {
                val user = UserDatabase(it).getUserDao().getAllUsers()

                adapter = UserAdapter(user)
                rvUserList.adapter = adapter
                rvUserList.layoutManager = LinearLayoutManager(activity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miLogOut -> {logoutUser()}
        }

        return super.onOptionsItemSelected(item)
    }

    private fun logoutUser() {
        val userPreference = activity?.let { SharedPreference(it) }
        if (userPreference != null) {
            if(userPreference.getValueString("userEmail")!=null){
                userPreference.clearSharedPreference()
                Toast.makeText(activity, "User Logged Out!!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_showUserFragment_to_loginFragment)
            }
            else{
                Toast.makeText(activity, "User Not Logged In!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}