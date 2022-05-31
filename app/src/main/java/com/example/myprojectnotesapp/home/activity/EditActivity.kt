package com.example.myprojectnotesapp.home.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myprojectnotesapp.MainActivity
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.databinding.ActivityEditBinding
import com.example.myprojectnotesapp.entity.Note
import com.example.myprojectnotesapp.method.DateChange
import com.example.myprojectnotesapp.viewModel.NotesViewModel

class EditActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEditBinding
    val editNoteExtra = "edit_note_extra"
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var note: Note
    private var isUpdate = false
    private val dateChange = DateChange()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
        initListener()

    }

    private fun initView() {

        if (intent.getParcelableExtra<Note>(editNoteExtra) != null) {

            isUpdate = true
            binding.buttonDelete.visibility = View.VISIBLE

            note = intent.getParcelableExtra(editNoteExtra)!!
            binding.editTextTitle.setText(note.title)
            binding.editTextBody.setText(note.body)
            binding.editTextTitle.setSelection(note.title.length)

            //set spinner position
            val compareValue = note.label
            val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.labels,
                android.R.layout.simple_spinner_item
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.spLabel.adapter = adapter

            val spinnerPosition = adapter.getPosition(compareValue)
            binding.spLabel.setSelection(spinnerPosition)

        }

    }

    private fun initViewModel() {
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
    }


    private fun initListener() {
        binding.toolbar.nibBack.setOnClickListener(this)
        binding.toolbar.btnSave.setOnClickListener(this)
        binding.buttonDelete.setOnClickListener(this)
    }

    private fun deleteNote(note: Note) {

        notesViewModel.deleteNote(note)
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
        Toast.makeText(this@EditActivity, "Note removed", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.nib_back -> {
                onBackPressed()
            }
            R.id.btn_save -> {
                val title = binding.editTextTitle.text.toString()
                val body = binding.editTextBody.text.toString()
                val label = binding.spLabel.selectedItem.toString()
                val date = dateChange.getToday()
                val time = dateChange.getTime()

                if (title.isEmpty() && body.isEmpty()) {
                    Toast.makeText(this@EditActivity, "Note cannot be empty", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (isUpdate) {
                        notesViewModel.updateNote(
                            Note(
                                id = note.id,
                                title = title,
                                label = label,
                                date = note.date,
                                time = note.time,
                                updatedDate = date,
                                updatedTime = time,
                                body = body
                            )
                        )
                    } else {
                        notesViewModel.insertNote(
                            Note(
                                title = title,
                                label = label,
                                date = date,
                                time = time,
                                body = body
                            )
                        )
                    }

                    Toast.makeText(this@EditActivity, "Note saved", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }

            }
            R.id.button_delete -> {
                deleteNote(note)
            }
        }
    }
}