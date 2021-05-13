package hf.yz.hfradiomanager_v2.utils.Data.User;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    /**
     * Select all users from the users table.
     *
     * @return all Users.
     */
    @Query("SELECT * FROM Users")
    List<User> getUsers();

    /**
     * Select user by name.
     * @param name the user name.
     * @return the required user.
     */
    @Query("SELECT * FROM Users WHERE username =:name")
    User findUserByName(String name);

    /**
     * Select user by ID.
     *
     * @param Id the user ID.
     * @return the required user.
     */
    @Query("SELECT * FROM Users WHERE userid =:Id")
    User findUserByID(String Id);

    /**
     * Insert a user in the database. If the User already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    /**
     * Update an user.
     * @param user user to be updated.
     * @return the number of user updated.
     */
    @Update
    int updateUser(User user);






}
