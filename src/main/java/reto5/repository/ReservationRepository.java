package reto5.repository;

import reto5.crudrepository.ReservationsCrudRepository;
import reto5.model.Client;
import reto5.model.Reservation;
import reto5.reports.CounterClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

        @Autowired
        private ReservationsCrudRepository reservationsCrudRepository;

        public List<Reservation> getAll(){

            return (List<Reservation>) reservationsCrudRepository.findAll();
        }
        public Optional<Reservation> getReservation(int idReservation){

            return reservationsCrudRepository.findById(idReservation);
        }
        public Reservation save(Reservation reservation){

            return reservationsCrudRepository.save(reservation);
        }
        public void delete(Reservation reservation){

            reservationsCrudRepository.delete(reservation);
        }




        public List<Reservation> getReservationByStatus(String status){
            return reservationsCrudRepository.findAllByStatus(status);
        }


        public List<Reservation> getReservationPeriod (Date a, Date b) {
            return reservationsCrudRepository.findAllByStartDateAfterAndStartDateBefore(a, b);
        }

        public List<CounterClients> getTopClients() {
            List<CounterClients> res= new ArrayList<>();
            List<Object[]>report = reservationsCrudRepository.countTotalReservationByClient();
            for(int i=0;i<report.size();i++){
                res.add(new CounterClients((Long)report.get(i)[1],(Client) report.get(i)[0]));
            }
        return res;

        }
}
