
//connection using gRPC

//package ma.fstt.microservice1auth.service;
//
//import ma.fstt.microservice1auth.*;
//import io.grpc.stub.StreamObserver;
//import net.devh.boot.grpc.server.service.GrpcService;
//
//import com.example.grpc.*;
//import  ma.fstt.microservice1auth.repository.UserCredentialRepository;
//import ma.fstt.microservice1auth.entity.*;
//
//
//@GrpcService
//public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {
//
//    @Override
//    public void getPerson(PersonRequest request, StreamObserver<PersonResponse> responseObserver) {
//        // Récupérez les données de la demande
//        String email = request.getEmail();
//
//        // Recherchez l'utilisateur dans votre base de données en utilisant l'email
//       // UserCredentialRepository userRepository = null;
//        UserCredential user = UserCredentialRepository.findByEmail(email);
//
//        // Préparez la réponse en fonction des données de l'utilisateur
//        PersonResponse.Builder responseBuilder = PersonResponse.newBuilder();
//        if (user instanceof PersonMorale) {
//            PersonMorale morale = (PersonMorale) user;
//            responseBuilder.setMorale(
//                    PersonMorale.newBuilder()
//                            .setNomSoc(morale.getNomSoc())
//                            .setNumSocie(morale.getNumSocie())
//                            .build()
//            );
//        } else if (user instanceof PersonPhysique) {
//            PersonPhysique physique = (PersonPhysique) user;
//            responseBuilder.setPhysique(
//                    PersonPhysique.newBuilder()
//                            .setNom(physique.getNom())
//                            .setPrenom(physique.getPrenom())
//                            .setNumeroTelephone(physique.getNumeroTelephone())
//                            .setDateNaissance(physique.getDateNaissance())
//                            .setAdresse(physique.getAdresse())
//                            .setCin(physique.getCin())
//                            .setNumPermis(physique.getNumPermis())
//                            .build()
//            );
//        }
//
//        // Envoyez la réponse
//        responseObserver.onNext(responseBuilder.build());
//
//        // Indiquez que vous avez terminé l'envoi de la réponse
//        responseObserver.onCompleted();
//    }
//
//}
