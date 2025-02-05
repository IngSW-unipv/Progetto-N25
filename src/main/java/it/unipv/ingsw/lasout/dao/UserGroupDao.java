package it.unipv.ingsw.lasout.dao;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.user.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDao{

        private static UserGroupDao instance = null;
        public static UserGroupDao getInstance(){
            if (instance == null){
                instance= new UserGroupDao();
            }
            return instance;
        }
        private UserGroupDao(){}

        public List<User> members(Group group) throws Exception{
            String sql = "SELECT * FROM £usergroup£ WHERE group_id = ?";
            DBQuery query = DatabaseUtil.getInstance().createQuery(sql, group.getId());

            DatabaseUtil.getInstance().executeQuery(query);
            ResultSet rs = query.getResultSet();

            List<User> user = new ArrayList<User>();
            while(rs.next()){
                User u = new User(rs.getInt("user_id"));
                user.add(u);
            }
            query.close();
            return user;
        }

        public List<Group> groups(User user) throws Exception{
            return null;
        }
        
    }