package com.qucat.quiz.repositories.dao.implementation;

import com.qucat.quiz.repositories.dao.FriendListDao;
import com.qucat.quiz.repositories.entities.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@PropertySource("classpath:friendlist.properties")
public class FriendListDaoImpl implements FriendListDao {
    @Autowired
    @Qualifier("postgresJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;
    @Value("#{${sql.friendList}}")
    private Map<String, String> friendsListQueries;

    @Override
    public boolean isSendNotification(int userId, NotificationType notificationType) {
        boolean isSend = false;
        switch (notificationType) {
            case CREATED_NEWS:
                isSend = jdbcTemplate.queryForObject(friendsListQueries.get("caseIsNewAnnouncement"),
                        new Object[]{userId}, boolean.class);
                break;
            case CREATED_QUIZ:
                isSend = jdbcTemplate.queryForObject(friendsListQueries.get("caseIsCreatedQuiz"),
                        new Object[]{userId}, boolean.class);
                break;
            case GAME_INVITATION:
                isSend = jdbcTemplate.queryForObject(friendsListQueries.get("caseIsGameInvitation"),
                        new Object[]{userId}, boolean.class);
                break;
            case FRIEND_INVITATION:
                isSend = jdbcTemplate.queryForObject(friendsListQueries.get("caseIsFriendInvitation"),
                        new Object[]{userId}, boolean.class);
                break;
            default:
                return false;
        }
        return isSend;
    }

    @Override
    public List<Integer> getForNotification(int userId, NotificationType notificationType) {
        List<Integer> friends;
        switch (notificationType) {
            case CREATED_NEWS:
                friends = jdbcTemplate.queryForList(friendsListQueries.get("caseNewAnnouncement"),
                        new Object[]{userId}, Integer.class);
                break;
            case CREATED_QUIZ:
                friends = jdbcTemplate.queryForList(friendsListQueries.get("caseNewQuiz"),
                        new Object[]{userId}, Integer.class);
                break;
            case GAME_INVITATION:
                friends = jdbcTemplate.queryForList(friendsListQueries.get("caseGameInvitation"),
                        new Object[]{userId}, Integer.class);
                break;
            case FRIEND_INVITATION:
                friends = jdbcTemplate.queryForList(friendsListQueries.get("caseFriendInvitation"),
                        new Object[]{userId}, Integer.class);
                break;
            case MESSAGE:
                friends = jdbcTemplate.queryForList(friendsListQueries.get("caseMessage"),
                        new Object[]{userId}, Integer.class);
                break;
            default:
                return null;
        }
        return friends;
    }
}
