package com.enotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enotes.entities.Notes;
import com.enotes.entities.User;
import com.enotes.repository.NotesRepository;

@Service
public class NotesServiceImpl implements NotesService{

	@Autowired
	private NotesRepository notesRepo;
	
	@Override
	public Notes saveNotes(Notes notes) {
		return notesRepo.save(notes);
	}

	@Override
	public Notes getNotesById(int id) {
		return notesRepo.findById(id).get();
	}

	@Override
	public List<Notes> getNotesByUser(User user) {
		return notesRepo.findByUser(user);
	}

	@Override
	public Notes updateNotes(Notes notes) {
		return notesRepo.save(notes);
	}

	@Override
	public boolean deleteNotes(int id) {
		Notes note=notesRepo.findById(id).get();
		if(note!=null) {
			notesRepo.delete(note);
			return true;
		}
		return false;
	}

}
