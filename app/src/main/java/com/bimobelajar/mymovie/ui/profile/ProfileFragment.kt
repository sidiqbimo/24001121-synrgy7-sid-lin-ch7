package com.bimobelajar.mymovie.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bimobelajar.mymovie.R

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var usernameInput: EditText
    private lateinit var fullNameInput: EditText
    private lateinit var birthdateInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var saveChangesButton: Button
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        profileViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(ProfileViewModel::class.java)

        usernameInput = view.findViewById(R.id.usernameInput)
        fullNameInput = view.findViewById(R.id.fullNameInput)
        birthdateInput = view.findViewById(R.id.birthdateInput)
        addressInput = view.findViewById(R.id.addressInput)
        saveChangesButton = view.findViewById(R.id.saveChangesButton)
        logoutButton = view.findViewById(R.id.logoutButton)

        profileViewModel.userName.observe(viewLifecycleOwner) { username ->
            usernameInput.setText(username)
        }

        profileViewModel.fullName.observe(viewLifecycleOwner) { fullName ->
            fullNameInput.setText(fullName)
        }

        profileViewModel.birthdate.observe(viewLifecycleOwner) { birthdate ->
            birthdateInput.setText(birthdate)
        }

        profileViewModel.address.observe(viewLifecycleOwner) { address ->
            addressInput.setText(address)
        }

        saveChangesButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val fullName = fullNameInput.text.toString()
            val birthdate = birthdateInput.text.toString()
            val address = addressInput.text.toString()
            profileViewModel.saveChanges(username, fullName, birthdate, address)
            Toast.makeText(context, "Changes saved", Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        return view
    }
}
