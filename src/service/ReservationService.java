package service;

import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Reservation;

import java.time.LocalDate;
import java.util.*;

public class ReservationService {
    // key: RoomNumber, value: Room
    private static Map<String, IRoom> roomHashMap = new HashMap<String, IRoom>();
    // key: RoomNumber, value: Reservation
    private static Map<String, Reservation> reservationHashMap = new HashMap<String, Reservation>();

    public void addRoom (IRoom room) {
        roomHashMap.put(room.getRoomNumber(), room);
    }

    public boolean isRoomExists(String roomNumber) {
        boolean res = true;
        if (roomHashMap.isEmpty() || !roomHashMap.containsKey(roomNumber)) {
            res = false;
        }
        return res;
    }

    public IRoom getARoom (String roomNumber) {
        return roomHashMap.get(roomNumber);
    }

    public Reservation reserveRoom (Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        String reservationKey = room.getRoomNumber() + "_" + customer.getEmail() + "_"
                + checkInDate.toString() + "_" + checkOutDate.toString();
        reservationHashMap.put(reservationKey, reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms (LocalDate checkInDate, LocalDate checkOutDate) {
        // Rooms in ReservationHashMap
        // checkIn >= reservation.checkOutDate
        // or (checkOut <= reservation.checkInDate )
        ArrayList<IRoom> roomArrayList = new ArrayList<IRoom>();
        ArrayList<String> roomNumberArrayList = new ArrayList<String>();
        Set<IRoom> roomSet = new HashSet<IRoom>();
        Set<String> roomNumberSet = new HashSet<String>();
        for (Reservation reservation : reservationHashMap.values()) {
            if (reservation.getCheckOutDate().compareTo(checkInDate) < 1
                    || reservation.getCheckInDate().compareTo(checkOutDate)>-1) {
                // roomArrayList.add(reservation.getRoom());   // redundent
                // roomSet.add(reservation.getRoom());
                roomNumberSet.add(reservation.getRoom().getRoomNumber());   // no redundant data. available rooms in reservation model
            }
        }

        for (String rNS : roomNumberSet) {
            roomNumberArrayList.add(rNS);   // 1. no redundant data. available rooms in reservation model
        }

        // Rooms in RoomHashMap, but not in ReservationHashMap
        Set<String> keysInRoomHashSet = new HashSet<String>(roomHashMap.keySet());   // all rooms in room model
        Set<String> keysInReservationHashMap = reservationHashMap.keySet(); // redundent. all data in reservation model
        Set<String> roomNumberInReservationSet = new HashSet<String>(); // no redundent. all data in reservation model

        for (String ks : keysInReservationHashMap) {
            String[] ksSplit = ks.split("_");
            roomNumberInReservationSet.add(ksSplit[0]);
            System.out.println(">>>>>>>>>>" + ksSplit[0] + "<<<<<<<<<<<");
        }

        System.out.println("===" + reservationHashMap.keySet().size() + " " + reservationHashMap.values().size());
        System.out.println("===" + reservationHashMap.values());

        //keysInRoomHashMap.removeAll(keysInReservationHashMap);
        keysInRoomHashSet.removeAll(roomNumberInReservationSet);

        for (String roomId : keysInRoomHashSet) {
            //roomArrayList.add(roomHashMap.get(roomId));
            roomNumberArrayList.add(roomId);    // 2. Those rooms not in reservation list
        }

        // return rooms by RoomIds
        for (String rNAL : roomNumberArrayList) {
            roomArrayList.add(roomHashMap.get(rNAL));
        }

        return roomArrayList;
    }

    public Collection<Reservation> getCustomersReservation (Customer customer) {
        Collection<Reservation> reservationCollection = new ArrayList<Reservation>();
        for (Reservation reservation : reservationHashMap.values()) {
            if (customer.equals(reservation.getCustomer())) {
                reservationCollection.add(reservation);
            }
        }
        return reservationCollection;
    }

    public void printAllRoom () {

        if (roomHashMap.isEmpty()) {
            System.out.println("No room");
        }

        for (IRoom room : roomHashMap.values()) {
            System.out.println(room);
        }
    }

    public void printAllReservation () {

        if (reservationHashMap.isEmpty()) {
            System.out.println("No reservation");
        }

        for (Reservation reservation : reservationHashMap.values()) {
            System.out.println(reservation);
        }
    }
}
