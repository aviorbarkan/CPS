package server.db.queries;

import server.db.DBConstants.DbSqlColumns;
import server.db.DBConstants.SqlTables;






public class RegularQueries {
	private static volatile RegularQueries instance;
	private static Object mutex = new Object();

	private RegularQueries() {
	}

	public static RegularQueries getInstance() {
		RegularQueries result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new RegularQueries();
			}
		}
		return result;
	}
	
	
	
	public final String get_last_day_complains =
			"SELECT * " +
			"FROM " + SqlTables.COMPLAINTS.getName() + 
			"  WHERE " + DbSqlColumns.COMPLAINT_DATETIME.getName()  +
			" BETWEEN ? AND ?";
	
	public final String car_id_details = 
			"SELECT * " +
			"FROM " +  SqlTables.CARS.getName() + 
			"  WHERE " + DbSqlColumns.CAR_ID.getName() + " = ?";
	
	
	public final String select_complain_details =
			"SELECT * " +
			"FROM " +  SqlTables.COMPLAINTS.getName() +
			" WHERE " + DbSqlColumns.COMPLAINT_ID.getName() + " = ?";

	public final String select_complaints_last_day = 
			"SELECT * " +
			" FROM " +  SqlTables.COMPLAINTS.getName() +
			" WHERE " + DbSqlColumns.COMPLAINT_DATETIME.getName() +
			" BETWEEN ? and ?";
	
	public final String update_customer_balance =
			" UPDATE  " + SqlTables.ACCOUNTS.getName() +
			" SET " + DbSqlColumns.BALANCE.getName() + " = " + DbSqlColumns.BALANCE.getName() + " + ? " +
			" WHERE " + DbSqlColumns.ACCOUNT_ID.getName() + " = ?";
	
	public final String select_customer_id_by_entrance_id = 
			"SELECT " + DbSqlColumns.ACCOUNT_ID.getName()  +
			" FROM " + SqlTables.CURRENT_CARS_PLANED_BEING_IN_PARKING.getName()+ 
			"  WHERE " + DbSqlColumns.ENTRANCE_ID.getName() + " = ?";
	
	
	public final String insert_car_planed_being_in_parking_to_log = 
			"INSERT INTO " + SqlTables.CURRENT_CARS_PLANED_BEING_IN_PARKING_LOG.getName()+
			"(" +
			 DbSqlColumns.CAR_ID.getName() + ", " +
			DbSqlColumns.ACCOUNT_ID.getName() + ", " +
			 DbSqlColumns.LOT_ID.getName() + ", " + 
			 DbSqlColumns.ORDER_TYPE.getName() + ", " +
			 DbSqlColumns.ARRIVE_PREDICTION.getName() + ", " +
			 DbSqlColumns.LEAVE_PREDICTION.getName() + ", " +
			 DbSqlColumns.ARRIVE_TIME.getName() + ", " +
			  DbSqlColumns.LEAVE_TIME.getName() + 
			 " ) " +
			  " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	
	public final String insert_car_planed_being_in_parking =
			"INSERT INTO " + SqlTables.CURRENT_CARS_PLANED_BEING_IN_PARKING.getName() + 
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	
	public final String car_left_parking_update_time_left = 
			" UPDATE  " + SqlTables.CURRENT_CARS_PLANED_BEING_IN_PARKING_LOG.getName() + 
			" SET " + DbSqlColumns.LEAVE_TIME.getName() + " = ? "
			+ " WHERE " + DbSqlColumns.ENTRANCE_ID.getName() + " = ?";
	
	public final String delete_entrance_from_car_planed_being_in_parking = 
			"DELETE FROM " + SqlTables.CURRENT_CARS_PLANED_BEING_IN_PARKING.getName()+
			" WHERE " + DbSqlColumns.ENTRANCE_ID.getName() + " = ?";
	

	public final String track_order =
			"SELECT * " +
			 "	FROM " + SqlTables.CURRENT_CARS_PLANED_BEING_IN_PARKING.getName()+
			 "  WHERE " + DbSqlColumns.ENTRANCE_ID.getName() + " = ?";
	

	public final String insert_new_account = 
			"INSERT INTO " + SqlTables.ACCOUNTS.getName() +
			" VALUES (?, ?, ?, ?, ?) ";
	
	public final String select_account_details = 
			"SELECT * " +
			"FROM  " + SqlTables.ACCOUNTS.getName() +
			" WHERE " + DbSqlColumns.ACCOUNT_ID.getName() + " = ?";
	
	
	public final String select_all_cars = 
			"SELECT * " +
			"FROM " + SqlTables.CARS.getName();
	
	
	/*select all cars in parking (including broken, order etc..*/ //TODO: fix this
	public final String select_all_cars_in_parking =
			"SELECT * " +
			" FROM " + SqlTables.CURRENT_CARS_PLANED_BEING_IN_PARKING.getName();
	

	
	
}