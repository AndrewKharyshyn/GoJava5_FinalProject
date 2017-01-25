package Objects;
/**
 * Класс пользователь
 */
public class User {
    private long userId;
    private String userName;
    private String userLastName;
    /**
     * Данный Пользователь с параметрами:
     * @param userId Уникальный идентификатор пользоватедя
     * @param userName Имя пользователя
     * @param userLastName Фамилия пользователя
     */
    public User(long userId, String userName, String userLastName) {
        this.userId = userId;
        this.userName = userName;
        this.userLastName = userLastName;
    }
    /**
     * Метод для корректного вывода информации об пользователе
     * @return параметры пользователя
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (!userName.equals(user.userName)) return false;
        return userLastName.equals(user.userLastName);

    }
    /**
     * Метод вызываеться при сравнивании содержимого обьектов (пользователей)
     * @param o данный пользователь
     * @return эквивалентность обьектов
     */
    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + userName.hashCode();
        result = 31 * result + userLastName.hashCode();
        return result;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
