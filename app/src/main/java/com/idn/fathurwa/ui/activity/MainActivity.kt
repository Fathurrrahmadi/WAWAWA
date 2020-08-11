package com.idn.fathurwa.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.idn.fathurwa.Adapter.SectionPagerAdapter
import com.idn.fathurwa.R
import com.idn.fathurwa.ui.ProfileActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance() // connect ke Firebase Authentication

    private var mSectionPagerAdapter: SectionPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
        resizeTabs()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 ->fab.hide()
                    1 ->fab.show()
                    2 ->fab.hide()
                }
            }

        })
        fab.setOnClickListener {
            onNewChat()
        }

        setSupportActionBar(toolbar) // menambahkan toolbar dari layout
        mSectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        container.adapter = mSectionPagerAdapter
        fab.setOnClickListener {
            Snackbar.make(it, "Replace with action", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
    }

    private fun onNewChat() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)
            ) {
                AlertDialog.Builder(this)
                    .setTitle("Contacts Permission")
                    .setMessage("This App Requires Access to Your Contacts to Initiation A Concersation")
                    .setPositiveButton("Yes") { dialog, which ->
                        requestContactPermission() // meminta izin membaca kontak untuk aplikasi
                    }
                    .setNegativeButton("No") { dialog, which ->
                    }
                    .show()
            }
            else {
                requestContactPermission()
            }
        } else {
            startNewActivity()
        }
    }

    private fun startNewActivity() {
        TODO("Not yet implemented")
    }

    private fun requestContactPermission() {
        TODO("Not yet implemented")
    }

    private fun resizeTabs() {
        val layout = (tabs.getChildAt(0 ) as LinearLayout).getChildAt(0) as LinearLayout
        val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 0.4f
        layout.layoutParams = layoutParams
    }

    override fun onResume() {
        super.onResume()
        if (firebaseAuth.currentUser == null){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

//    class PlaceHolderFragment : Fragment() {
//        companion object {
//            private val ARG_SECTION_NUMBER = "section number"
//
//            fun newIntent(sectionNumber: Int): PlaceHolderFragment {
//                val fragment =
//                    PlaceHolderFragment()
//                val args = Bundle() //mengikat data untuk dikirimkan bersamaan
//                args.putInt(ARG_SECTION_NUMBER, sectionNumber) // mengirimkan data
//                fragment.arguments = args
//                return fragment
//            }
//        }
//
//        @SuppressLint("SetTextI18n")
//        override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//        ): View? {
//            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
//            rootView.section_label.text =
//                "Hello world, from section ${arguments?.getInt(ARG_SECTION_NUMBER)}"
//            return rootView
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_name, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> onLogout()
            R.id.action_profile -> onProfile()

        }

        return super.onOptionsItemSelected(item)
    }

    private fun onProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    private fun onLogout() {
        firebaseAuth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}