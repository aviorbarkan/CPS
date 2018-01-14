package server.db.dbAPI; 

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import server.db.DBConnection;
import server.db.DBConnection.sqlTypeKind;
import server.db.DBConstants;
import server.db.DBConstants.TrueFalse;
import server.db.queries.ParkingMapQueries;
import server.db.queries.RegularQueries;

// TODO: Auto-generated Javadoc
/**
 * The Class RegularDBAPI.
 */
public class RegularDBAPI extends DBAPI{

	/** The instance. */
	private static volatile RegularDBAPI instance;
	
	/** The regular queries inst. */
	private RegularQueries regularQueriesInst;

	/**
	 * Instantiates a new regular DBAPI.
	 */
	private RegularDBAPI() {
		regularQueriesInst = RegularQueries.getInstance();
		parkingMapQueriesInst = ParkingMapQueries.getInstance();
	}

	/**
	 * Gets the single instance of RegularDBAPI.
	 *
	 * @return single instance of RegularDBAPI
	 */
	public static RegularDBAPI getInstance() {
		RegularDBAPI result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new RegularDBAPI();
			}
		}
		return result;
	}


	/**
	 * Insert parking reservation.
	 *
	 * @param carId the car id
	 * @param accountId the account id
	 * @param lotId the lot id
	 * @param predictionArrive the prediction arrive
	 * @param predictionLeave the prediction leave
	 * @param timeArrive the time arrive
	 * @param timeLeave the time leave
	 * @param orderType the order type
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int insertParkingReservation(String carId, int accountId,/* int entranceId,*/ int lotId, Date predictionArrive,
			Date predictionLeave, Date timeArrive, Date timeLeave,
			/*DBConnection.orderType*/DBConstants.OrderType orderType/*order, occasional entrance, etc*/) throws SQLException {
		Queue<Object> paramsValues = new LinkedList<Object>(); // push all params to paramsValues. in order of the SQL query
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all param types to paramTypes. in order of the SQL query
		paramsValues.add(carId);
		paramTypes.add(DBConnection.sqlTypeKind.VARCHAR);
		paramsValues.add(accountId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		//		paramsValues.add(entranceId);
		//		paramTypes.add(DBConnection.sqlTypeKind.INT);
		paramsValues.add(lotId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		paramsValues.add(orderType.getValue());
		paramTypes.add(DBConnection.sqlTypeKind.VARCHAR);
		paramsValues.add(predictionArrive);
		paramTypes.add(DBConnection.sqlTypeKind.TIMESTAMP);
		paramsValues.add(predictionLeave);
		paramTypes.add(DBConnection.sqlTypeKind.TIMESTAMP);
		paramsValues.add(timeArrive);
		paramTypes.add(DBConnection.sqlTypeKind.TIMESTAMP);
		paramsValues.add(timeLeave);
		paramTypes.add(DBConnection.sqlTypeKind.TIMESTAMP);
		int entranceId = DBConnection.updateSql(regularQueriesInst.insert_car_planed_being_in_parking_to_log, paramsValues, paramTypes);
		paramsValues.add(entranceId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		paramsValues.add(carId);
		paramTypes.add(DBConnection.sqlTypeKind.VARCHAR);
		paramsValues.add(accountId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		paramsValues.add(lotId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		paramsValues.add(orderType.getValue());
		paramTypes.add(DBConnection.sqlTypeKind.VARCHAR);
		paramsValues.add(predictionArrive);
		paramTypes.add(DBConnection.sqlTypeKind.TIMESTAMP);
		paramsValues.add(predictionLeave);
		paramTypes.add(DBConnection.sqlTypeKind.TIMESTAMP);
		paramsValues.add(timeArrive);
		paramTypes.add(DBConnection.sqlTypeKind.TIMESTAMP);
		paramsValues.add(timeLeave);
		paramTypes.add(DBConnection.sqlTypeKind.TIMESTAMP);
		DBConnection.updateSql(regularQueriesInst.insert_car_planed_being_in_parking, paramsValues, paramTypes);
		return entranceId;
	}




	/**
	 * Insert new order.
	 *
	 * @param accountId the account id
	 * @param carId the car id
	 * @param lotId the lot id
	 * @param predictionArrive the prediction arrive
	 * @param predictionLeave the prediction leave
	 * @param timeArrive the time arrive
	 * @param timeLeave the time leave
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int insertNewOrder(/*int entranceId,*/ int accountId, String carId, int lotId, Date predictionArrive, Date predictionLeave, Date timeArrive, Date timeLeave)
			throws SQLException {
		return insertParkingReservation(carId, accountId,/* entranceId,*/ lotId, predictionArrive, predictionLeave, timeArrive, timeLeave, DBConstants.OrderType.ORDER);
	}

	/**
	 * Select order status.
	 *
	 *select all details of the reservation (includes: one time order, regular order and any other kind that is inside the parking right now).
	 *
	 * @param entranceId the entrance id
	 * @param resultList the result list
	 * @throws SQLException the SQL exception
	 */
	public void selectOrderStatus(int entranceId, ArrayList<Map<String, Object>> resultList)
			throws SQLException {
		Queue<Object> paramsValues = new LinkedList<Object>(); // push all params to q. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to q. in order of SQL
		paramsValues.add(entranceId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		DBConnection.selectSql(regularQueriesInst.track_order, paramsValues, paramTypes, resultList);
	}

	/**
	 * Select account details.
	 *
	 * @param accountId the account id
	 * @param resultList the result list
	 * @throws SQLException the SQL exception
	 */
	public void selectAccountDetails(int accountId, ArrayList<Map<String, Object>> resultList)
			throws SQLException {
		Queue<Object> q = new LinkedList<Object>(); // push all params to q. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to q. in order of SQL
		q.add(accountId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		DBConnection.selectSql(regularQueriesInst.select_account_details, q, paramTypes, resultList);
	}


	/**
	 * Insert new account.
	 *
	 * create a new account in the db
	 *
	 * @param accountId the account id
	 * @param email the email
	 * @param carId the car id
	 * @param hasSubscription the has subscription
	 * @throws SQLException the SQL exception
	 */
	public void insertNewAccount(int accountId, String email, String carId, DBConstants.TrueFalse hasSubscription)
			throws SQLException {
		double balance = 0;
		Queue<Object> q = new LinkedList<Object>(); // push all params to q. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to q. in order of SQL
		q.add(accountId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		q.add(email);
		paramTypes.add(DBConnection.sqlTypeKind.VARCHAR);
		q.add(carId);
		paramTypes.add(DBConnection.sqlTypeKind.VARCHAR);
		q.add(balance);
		paramTypes.add(DBConnection.sqlTypeKind.DOUBLE);
		q.add((hasSubscription.getValue()));
		paramTypes.add(DBConnection.sqlTypeKind.VARCHAR);
		DBConnection.updateSql(regularQueriesInst.insert_new_account, q, paramTypes);
	}


	/**
	 * Select customer account details.
	 *
	 * selects all customer account details.
	 *
	 * @param customerId the customer id
	 * @param resultList the result list
	 * @throws SQLException the SQL exception
	 */
	public void selectCustomerAccountDetails(int customerId, ArrayList<Map<String, Object>> resultList) throws SQLException {
		Queue<Object> params = new LinkedList<Object>(); // push all params to paramsValues. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to paramsValues. in order of SQL
		params.add(customerId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		DBConnection.selectSql(regularQueriesInst.select_account_details, params, paramTypes, resultList);
	}


	/**
	 * Car left parking.
	 * 
	 * updates in logs table the leaving time.
	 * deletes from the table of current cars in parking.
	 *
	 * @param entranceId the entrance id
	 * @param timeLeft the time left
	 * @throws SQLException the SQL exception
	 */
	public void carLeftParking(int entranceId, Date timeLeft) throws SQLException {
		Queue<Object> paramsValues = new LinkedList<Object>(); // push all params to paramsValues. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to paramsValues. in order of SQL
		paramsValues.add(timeLeft);
		paramTypes.add(DBConnection.sqlTypeKind.TIMESTAMP);
		paramsValues.add(entranceId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		DBConnection.updateSql(regularQueriesInst.car_left_parking_update_time_left, paramsValues, paramTypes);
		paramsValues.add(entranceId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);		
		DBConnection.updateSql(regularQueriesInst.delete_entrance_from_car_planed_being_in_parking, paramsValues, paramTypes);
	}

	/**
	 * Selects the complaint details.
	 * selects all the complaingId details.
	 * @param complainId the complain id
	 * @param resultList the result list
	 * @return the complain details
	 * @throws SQLException the SQL exception
	 */
	public void selectComplaintDetails(int complainId, ArrayList<Map<String, Object>> resultList) throws SQLException {
		Queue<Object> params = new LinkedList<Object>(); // push all params to paramsValues. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to paramsValues. in order of SQL
		params.add(complainId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		DBConnection.selectSql(regularQueriesInst.select_complain_details, params, paramTypes, resultList);
	}


	/**
	 * Update customer balance.
	 *
	 * @param customerId the customer id
	 * @param valueInCashToAdd the value in cash to add
	 * @throws SQLException the SQL exception
	 */
	public void updateCustomerBalance(int customerId, double valueInCashToAdd) throws SQLException {
		Queue<Object> paramsValues = new LinkedList<Object>(); // push all params to paramsValues. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to paramsValues. in order of SQL
		paramsValues.add(valueInCashToAdd);
		paramTypes.add(DBConnection.sqlTypeKind.DOUBLE);
		paramsValues.add(customerId);
		paramTypes.add(DBConnection.sqlTypeKind.INT);
		DBConnection.updateSql(regularQueriesInst.update_customer_balance, paramsValues, paramTypes);
	}

	/**
	 * Cancel order.
	 * 
	 * updates in logs table the leaving time.
	 * deletes from the table of current cars in parking.
	 * TODO: for now it also updates in the daily stats the canceled orders for today. maybe delete it, and do it once in a week.
	 * 
	 * @param entrance_id the entrance id
	 * @param valueInCashToAddReduce the value in cash to add reduce
	 * @throws SQLException the SQL exception
	 */
	public void cancelOrder(int entrance_id, double valueInCashToAddReduce) throws SQLException {
		Queue<Object> paramsValues = new LinkedList<Object>(); // push all params to paramsValues. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to paramsValues. in order of SQL
		paramsValues.add(entrance_id);
		paramTypes.add(sqlTypeKind.INT);
		ArrayList<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
		DBConnection.selectSql(regularQueriesInst.select_customer_id_by_entrance_id, paramsValues, paramTypes, rs);
		int customerId = (int) rs.get(0).get("account_id");
		paramsValues.add(entrance_id);
		paramTypes.add(sqlTypeKind.INT);	
		DBConnection.updateSql(regularQueriesInst.delete_entrance_from_car_planed_being_in_parking, paramsValues, paramTypes);

		Calendar todayCalendar = new GregorianCalendar();
		java.sql.Date today = new java.sql.Date(todayCalendar.getTimeInMillis());
		paramsValues.add(today);
		paramTypes.add(DBConnection.sqlTypeKind.DATE);

		updateCustomerBalance(customerId, valueInCashToAddReduce);
	}


	//	TODO: still need to check if it is possible to create the array inside the function. for now, the user should create an array with the appropriate size.
	/**
	 * Select parking map by lot id.
	 *
	 * @param lotId the lot id
	 * @param parkingMapArr the parking map array - called with empty array. returned with a full one.
	 * @throws SQLException the SQL exception
	 */
	public void selectParkingMapByLotId(int lotId, String [] parkingMapArr) throws SQLException {

		Queue<Object> paramsValues = new LinkedList<Object>(); // push all params to paramsValues. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to paramsValues. in order of SQL
		paramsValues.add(lotId);
		paramTypes.add(sqlTypeKind.INT);
		ArrayList<Map<String, Object>> rs = new ArrayList<Map<String,Object>>();
		DBConnection.selectSql(parkingMapQueriesInst.select_parking_map_by_lot_id, paramsValues, paramTypes, rs);
		Iterator<Map<String, Object>> iterator = rs.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> row = (Map<String, Object>) iterator.next();
			for (int i = 0; i < parkingMapArr.length; i++) {
				String curIdx = "c"+(i+1);
				parkingMapArr[i] = (String)row.get(curIdx);

			}
		}
	}

	/**
	 * Insert parking map of lot id.
	 *
	 * @param lotId the lot id
	 * @param parkingMapArr the parking map arr
	 * @throws SQLException the SQL exception
	 */
	public void insertParkingMapOfLotId(int lotId, String [] parkingMapArr) throws SQLException {

		Queue<Object> paramsValues = new LinkedList<Object>(); // push all params to paramsValues. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to paramsValues. in order of SQL
		paramsValues.add(lotId);
		paramTypes.add(sqlTypeKind.INT);	
		for (int i = 0; i < parkingMapArr.length; i++) {
			paramsValues.add(parkingMapArr[i]);

			paramTypes.add(sqlTypeKind.VARCHAR);			
		}
		DBConnection.updateSql(parkingMapQueriesInst.parkingMapInsertQueriesIdxByLotId[lotId-1], paramsValues, paramTypes);
	}

	/**
	 * Delete parking map.
	 *
	 * @param lotId the lot id
	 * @throws SQLException the SQL exception
	 */
	public void deleteParkingMap(int lotId) throws SQLException {
		Queue<Object> paramsValues = new LinkedList<Object>(); // push all params to paramsValues. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to paramsValues. in order of SQL
		paramsValues.add(lotId);
		paramTypes.add(sqlTypeKind.INT);
		DBConnection.updateSql(parkingMapQueriesInst.delete_parking_map_of_lot_id, paramsValues, paramTypes);

	}
	
	/**
	 * Insert new complaint.
	 *
	 * @param customerId the customer id
	 * @param complaintDescription the complaint description
	 * @param entranceId the entrance id
	 * @param lotId the lot id
	 * @param complaintTime the complaint time
	 * @return the complaint ID
	 * @throws SQLException the SQL exception
	 */
	public int insertComplaint(int customerId, String complaintDescription, int entranceId, int lotId, Date complaintTime) throws SQLException {
		Queue<Object> paramsValues = new LinkedList<Object>(); // push all params to paramsValues. in order of SQL
		Queue<DBConnection.sqlTypeKind> paramTypes = new LinkedList<DBConnection.sqlTypeKind>(); // push all params to paramsValues. in order of SQL
		
		paramsValues.add(customerId);
		paramTypes.add(sqlTypeKind.INT);		
		paramsValues.add(lotId);
		paramTypes.add(sqlTypeKind.INT);
		paramsValues.add(complaintDescription);
		paramTypes.add(sqlTypeKind.VARCHAR);
		paramsValues.add(entranceId);
		paramTypes.add(sqlTypeKind.INT);		
		paramsValues.add(TrueFalse.FALSE.getValue());
		paramTypes.add(sqlTypeKind.VARCHAR);
		paramsValues.add(complaintTime);
		paramTypes.add(sqlTypeKind.TIMESTAMP);		
		
		int complaintId = DBConnection.updateSql(regularQueriesInst.insert_complaint, paramsValues, paramTypes);
		return complaintId;
	}
	
	

	/**
	 * Select last day complaints.
	 *
	 * @param resultList the result list - contains all the records of complaints of yesterday. each record is an entry in the ArrayList. 
	 * each pair inside Map: ((Sring) columnName, (Object) value) is a column and it's value.
	 * @throws SQLException the SQL exception
	 */
	public void selectLastDayComplaints(ArrayList<Map<String, Object>> resultList) throws SQLException{

		Calendar calendar = new GregorianCalendar();
		java.sql.Date today = new java.sql.Date(calendar.getTimeInMillis());
		calendar.add(Calendar.DATE, -1); //get a day back
		java.sql.Date yesterday = new java.sql.Date(calendar.getTimeInMillis());

		selectBetween2DatesQuery(regularQueriesInst.select_complaints_last_day, yesterday, today, resultList);
	}



	/**
	 * Update parking reservaion.
	 *
	 * @param carID the car ID
	 * @param customerID the customer ID
	 * @param entranceId the entrance id
	 * @param parkingLotID the parking lot ID
	 * @param arrivalTime the arrival time
	 * @param estimatedDepartureTime the estimated departure time
	 * @param date the date
	 * @param date2 the date 2
	 * @param value the value
	 */
	public void updateParkingReservaion(String carID, int customerID, int entranceId, int parkingLotID,
			Date arrivalTime, Date estimatedDepartureTime, Date date, Date date2, String value) {
		// TODO Auto-generated method stub

	}




}
