package db;

public class mysqlDB {
        private String url = "jdbc:mysql://localhost:3306/inventoryms";
	private String username = "root";
	private String password = "";
        private String className = "com.mysql.jdbc.Driver";

        public String getUrl() {
            return url;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
        
}
