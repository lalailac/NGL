@Dao
public interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY timestamp DESC")
    LiveData<List<Note>> getAllNotes();

    @Insert
    void insert(Note note);
}
