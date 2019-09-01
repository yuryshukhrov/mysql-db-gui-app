# MYSQL GUI Database Application

## Overview

Application's front-end is built around Swing and includes:
* menu items for choosing actions:
  * connecting to database,
  * reading data (SELECT in SQL),
  * inserting data (INSERT in SQL),
  * updating data (UPDATE in SQL),
  * disconnecting from database;
* input field(s) for presenting the database query (SQL statement);
* area for viewing data in a table (in case of reading);
* area for viewing an input form (in case of inserting/updating).

Application's back-end is a MySQL database accessible via JDBC. You can find the compiled application in **dist** folder. Here is the general look of the application:

![](https://raw.githubusercontent.com/yuryshukhrov/mysql-db-gui-app/master/src/images/mysql_db_gui_app.png)

All features are described in the documentation that is available under "Help".




## Author

**Yury Shukhrov**

## License

Copyright © 2019, [Yury Shukhrov](https://github.com/yuryshukhrov).
Released under the [MIT License](LICENSE).

