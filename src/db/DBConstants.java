package db;

import db.DBConnection.sqlTypeKind;

// These constants must be the same as the column names in the DB 
public class DBConstants {
	
	public enum sqlColumns{
		ENTRANCE_ID("entrance_id", DBConnection.sqlTypeKind.INT),
		LEAVE_TIME("leave_time", DBConnection.sqlTypeKind.TIMESTAMP),
		ACCOUNT_ID("account_id", DBConnection.sqlTypeKind.INT),
		EMAIL("email", DBConnection.sqlTypeKind.VARCHAR),		
		LEAVE_PREDICTION("leave_prediction", DBConnection.sqlTypeKind.TIMESTAMP),
		ORDER_TYPE("order_type", DBConnection.sqlTypeKind.ENUM),
		ARRIVE_PREDICTION("arrive_prediction", DBConnection.sqlTypeKind.VARCHAR),
		ARRIVE_TIME("arrive_time", DBConnection.sqlTypeKind.VARCHAR),
		CAR_ID("car_id", DBConnection.sqlTypeKind.INT),
		FILLED_RESERVATIONS("filled_reservations", DBConnection.sqlTypeKind.INT),
		LOT_ID("lot_id", DBConnection.sqlTypeKind.INT),
		ROLE_ID("role_id", DBConnection.sqlTypeKind.ENUM), 
		PASSWORD("password", DBConnection.sqlTypeKind.VARCHAR),
		HAS_SUBSCRIPTION("has_subscription", DBConnection.sqlTypeKind.ENUM),
		SUBSCRIPTION_ID("subscription_id", DBConnection.sqlTypeKind.INT),
		EXPIRED_DATE("subscription_id", DBConnection.sqlTypeKind.TIMESTAMP),
		DAY_ID("day_id", DBConnection.sqlTypeKind.DATE),
		BALANCE("balance", DBConnection.sqlTypeKind.DOUBLE),
		CANCELED_ORDERS("canceled_reservations", DBConnection.sqlTypeKind.INT),
		LATING_PER_PARK("latings_per_park", DBConnection.sqlTypeKind.INT),
		COMPLAINT_ID("complaint_id", DBConnection.sqlTypeKind.INT),
		COMPLAINT_DATETIME("date_complaint", DBConnection.sqlTypeKind.TIMESTAMP),
		COMPLAINT_RESULT("complaint_result", DBConnection.sqlTypeKind.TEXT),
		COMPLAINT_CUSTOMER_SERVICE_RESPOND("customer_service_response", DBConnection.sqlTypeKind.TEXT),
		COMPLAINT_DESCRIPTION("complaint_description", DBConnection.sqlTypeKind.VARCHAR),
		COMPLAINT_FILLED("complaint_filled", DBConnection.sqlTypeKind.ENUM),
		WORKER_ID("worker_id", DBConnection.sqlTypeKind.INT)
		;
		String columnName;
		sqlTypeKind type;
		sqlColumns(String columnName, sqlTypeKind columnType){
			 this.columnName = columnName; 
			 this.type = columnType;
		}
		public String getName(){
			return columnName;
		}
		public sqlTypeKind getType(){
			return type;
		}
	};
	
	public enum sqlTables {
		COMPLAINTS("complaints"),
		CURRENT_CARS_PLANED_BEING_IN_PARKING("current_cars_planed_being_in_parking"),
		CURRENT_CARS_PLANED_BEING_IN_PARKING_LOG("current_cars_planed_being_in_parking_log"),
		ACCOUNTS("accounts"),
		CARS("cars"),
		WORKERS("workers"),
		SUBSCRIPTIONS("subscriptions"),
		ROLES(""),
		RATES("rates"),
		PARKING_MAP("parking_map"),
		PERMISSIONS(""),
		DAILY_STATS("daily_stats"),
		LOT_DIMENSIONS("lot_num_of_columns"),
		WEEKLY_STATS("");
		
		private String tableName;
		sqlTables(String tableName){
			this.tableName = tableName;
		}
		
		public String getName() {
			return tableName;
		}
		
	};
	
	
	public enum parkingMap {FREE("free"), PARKED("parked"), RESERVED("reserved"), BROKEN("broken"), MAINTENENCE("maintenence");
		String value;
		parkingMap(String value) {
		      this.value = value;
		}
	    public String getValue() {
		      return value;
	    }
	    public static parkingMap convertStringToParkingMapEnum(String s) {
	        switch (s) {
	         case "free":
	 	    	return FREE;
	         case "parked":
	        	 return PARKED;
	         case "reserved":
	        	 return RESERVED;
	         case "broken":
	             return BROKEN;
	         case "maintenence":
	        	 return MAINTENENCE;
	         default:
	             throw new IllegalArgumentException("Invalid value: " + s);
	        }
	    }
	};
	
	public enum orderType {ONE_TIME("oneTime"), ORDER("order"), SUBSCRIPTION("subscriptionRegular"), SUBSCRIPTION_FULL("subscriptionFull");
		String value;
		orderType(String value) {
		      this.value = value;
	   }
		public String getValue() {
		      return value;
		}
	};
	
	public enum trueFalse {TRUE("true"), FALSE("false") ;
		String value;
		trueFalse(String value){
			this.value = value;
		}
		
		public String getValue() {
		      return value;
		}
	}
	
	public enum Role {LOT_MGR("lotMgr"), FIRM_MGR("firmMgr"), CUSTOMER_SERVICE("customerService");
		
		String name;
		Role(String name){};
		
		public String getName() {
			return name;
		}
		
	};

	
	
	// current_cars_planed_being_in_parking columns
//	final public static String ENTRANCE_ID = "entrance_id";
//	final public static String LEAVE_TIME = "leave_time";
//	final public static String ACCOUNT_ID = "account_id";
//	final public static String EMAIL = "email";
//	final public static String LEAVE_PREDICTION = "leave_prediction";
//	final public static String LOT_ID = "lot_id";
//	final public static String KIND = "kind";
//	final public static String ARRIVE_PREDICTION = "arrive_prediction";
//	final public static String CAR_ID = "car_id";
//	final public static String ARRIVE_TIME = "arrive_time";
//	final public static String FILLED_RESERVATIONS = "filled_reservations";
//	final public static String COMPLAINT_DESCRIPTION = "complaint_description";
//	final public static String COMPLAINT_FILLED = "complaint_filled";
//	final public static String COMPLAINT_CS_RESPONSE = "complaint_customer_service_response";
//	final public static String COMPLAINT_RESOLUTION = "complaint_resolution";
//	final public static String ROLE_ID = "role_id";
//	final public static String PASSWORD = "pwd";

}