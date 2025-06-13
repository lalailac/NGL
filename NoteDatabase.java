@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static volatile NoteDatabase INSTANCE;

    public static NoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        NoteDao dao = INSTANCE.noteDao();
                                        dao.insert(new Note("Sample note 1"));
                                        dao.insert(new Note("Sample note 2"));
                                    });
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
