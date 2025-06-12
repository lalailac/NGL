@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY timestamp DESC")
    fun getAllNotes(): LiveData<List<Note>>
}
