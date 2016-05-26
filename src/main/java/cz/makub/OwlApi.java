package cz.makub;

import com.clarkparsia.owlapi.explanation.DefaultExplanationGenerator;
import com.clarkparsia.owlapi.explanation.util.SilentExplanationProgressMonitor;
import static com.clarkparsia.owlapiv3.OWL.manager;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
//import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactoryimport java.util.Random;
//import static cz.makub.Random.anyRandomIntRange;
import java.io.File;
import static java.lang.ProcessBuilder.Redirect.to;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import org.semanticweb.owlapi.vocab.PrefixOWLOntologyFormat;
import uk.ac.manchester.cs.bhig.util.Tree;
import uk.ac.manchester.cs.owl.explanation.ordering.ExplanationOrderer;
import uk.ac.manchester.cs.owl.explanation.ordering.ExplanationOrdererImpl;
import uk.ac.manchester.cs.owl.explanation.ordering.ExplanationTree;
import uk.ac.manchester.cs.owlapi.dlsyntax.DLSyntaxObjectRenderer;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static jdk.nashorn.internal.objects.NativeJava.to;
import static org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory.IRI;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import static sun.java2d.cmm.ProfileDataVerifier.verify;
import static sun.security.x509.X509CRLImpl.verify;
import static sun.security.x509.X509CertImpl.verify;
//import static jdk.nashorn.internal.objects.NativeJava.to;

/**
 * Example how to use an OWL ontology with a reasoner.
 *
 * Run in Maven with
 * <code>mvn exec:java -Dexec.mainClass=cz.makub.Tutorial</code>
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
public class OwlApi {

    private static final String BASE_URL1 = "http://www.eecs.qmul.ac.uk/~sobhani/Test/Throw2.owl#";
    //private static final String BASE_URL = "http://acrab.ics.muni.cz/ontologies/tutorial.owl";
    private static final OWLObjectRenderer renderer = new DLSyntaxObjectRenderer();

    public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {

        // File file = new File("C:\\Users\\Sobhani\\Documents\\OWLTest\\save\\new.owl");
        //prepare ontology and reasoner
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        //OWLOntology ontology = manager.loadOntologyFromOntologyDocument(BASE_URL);
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(IRI.create(BASE_URL1));
        OWLReasonerFactory reasonerFactory = PelletReasonerFactory.getInstance();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology, new SimpleConfiguration());
        OWLDataFactory factory = manager.getOWLDataFactory();
        PrefixManager pm = new DefaultPrefixManager(BASE_URL1);
        //PrefixManager pm = (PrefixManager) manager.getOntologyFormat(ontology);
        //((PrefixOWLOntologyFormat)pm).setDefaultPrefix(BASE_URL1 + "#");
        //Map<String, String> prefixMap = pm.getPrefixName2PrefixMap();
        System.out.println(BASE_URL1 + " - " + pm.getDefaultPrefix());
        //pm.getDefaultPrefix());
        OWLClass EventC = factory.getOWLClass("Event", pm);
        OWLClass BreakingC = factory.getOWLClass("Breaking", pm);
        OWLClass DamageC = factory.getOWLClass("Damage", pm);
        OWLClass IllegalTC = factory.getOWLClass("IllegalT", pm);
        OWLClass KickC = factory.getOWLClass("Kick", pm);
        OWLClass MoveC = factory.getOWLClass("Move", pm);
        OWLClass FastMove = factory.getOWLClass("FastMove", pm);
        OWLClass PushC = factory.getOWLClass("Push", pm);
        OWLClass RunC = factory.getOWLClass("Run", pm);
        OWLClass ThrowC = factory.getOWLClass("Throw", pm);
        OWLClass ObjectC = factory.getOWLClass("Object", pm);
        OWLClass ArmC = factory.getOWLClass("Arm", pm);
        OWLClass CarC = factory.getOWLClass("Car", pm);
        OWLClass PersonC = factory.getOWLClass("Person", pm);
        OWLClass WindowsC = factory.getOWLClass("windows", pm);

        OWLObjectProperty participateIn = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "participateIn"));
        OWLObjectProperty partOf = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "partOf"));
        OWLObjectProperty participant = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "participant"));
        //OWLObjectProperty hasPart = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "hasPart"));

//        Random r = new Random();
//        Map<String, OWLIndividual> personMap = new HashMap<>();
//        for (int i = 0; i < 3; i++) {
//            String URL = BASE_URL1 + (Math.abs(r.nextInt(20)));
//            System.out.println("URL" + ":" + URL);
//            OWLIndividual personIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
//            System.out.println("piii" + ":" + personIndividual);
//            //Map<String, OWLIndividual> personMap = new HashMap<>();
//
//            personMap.put(URL, personIndividual);
//            System.out.println("Person Key is: " + personMap.get(URL));
//                    
//                 //   + " & " + " value is: " + personMap.get(personIndividual));
////            Set set = personMap.entrySet();
////            Iterator iterator = set.iterator();
////            //  while (iterator.hasNext()) {
////            Map.Entry personentry = (Map.Entry) iterator.next();
////            System.out.println("Person Key is: " + personentry.getKey() + " & " + " value is: " + personentry.getValue());
////                 for (Map.Entry entry: personMap.entrySet())
////                 System.out.println("Person Key is: " + entry.getKey()+ " & " + " value is: " + entry.getValue());
//            OWLClassAssertionAxiom personAsserion = factory.getOWLClassAssertionAxiom(PersonC, personIndividual);
//            manager.addAxiom(ontology, personAsserion);
//             //System.out.println("piii" + ":" + personIndividual);
////                File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_M_saved.owl");
////                manager.saveOntology(ontology, IRI.create(ontologySave.toURI()));
//
//      }
//        Map<String, OWLIndividual> eventMap = new HashMap<>();
//        for (int i = 0; i < 5; i++) {
//            String URL = BASE_URL1 + (Math.abs(r.nextInt(100)));
//            OWLIndividual eventIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
//          //  Map<String, OWLIndividual> eventMap = new HashMap<>();
//            eventMap.put(URL, eventIndividual);
//            
//          eventMap.put(URL, eventIndividual ); 
//          System.out.println("Person Key is: " +  eventMap.get(URL));
////            for (String key : eventMap.keySet()) {
////                System.out.println("Event Key is: " + key + " & " + " value is: " + eventMap.get(key));
////            Set set = eventMap.entrySet();
////            Iterator iterator = set.iterator();
////            // while (iterator.hasNext()) {
////            Map.Entry evententry = (Map.Entry) iterator.next();
////            System.out.println("Event Key is: " + evententry.getKey() + " & " + " value is: " + evententry.getValue());
//            OWLClassAssertionAxiom EventAsserion = factory.getOWLClassAssertionAxiom(EventC, eventIndividual);
//            manager.addAxiom(ontology, EventAsserion); //OWLObjectPropertyAssertionAxiom
//             //RunaxiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, personMap.get(set), eventMap.get(set));
//              OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, personMap.get(URL),eventMap.get(URL));
//           // OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, verify.get(keyC)),verify.get();
//        }
//    }

        
        
        Map<String, OWLIndividual> personMap = new HashMap<>();
        Map<String, OWLIndividual> eventMap = new HashMap<>();
        int p=0;
        
        for (int i = 0; i < 10; i++) {
            String URL = BASE_URL1 + ++p;
            System.out.println(URL);
            OWLIndividual personIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
           // System.out.println("piii" + ":" + personIndividual);
            personMap.put(URL, personIndividual);
            System.out.println("Person Key is: "+(personMap.get(URL)).toString());
//            Set set = personMap.entrySet();
//            Iterator iterator = set.iterator();
//            Map.Entry personentry = (Map.Entry) iterator.next();
//            System.out.println("Person Key is: " + personentry.getKey() + " & " + " value is: " + personentry.getValue());

            OWLClassAssertionAxiom personAsserion = factory.getOWLClassAssertionAxiom(PersonC, personIndividual);
            manager.addAxiom(ontology, personAsserion);
            
        }
        
        
             for (int i = 0; i < 10; i++) {
            String URL = BASE_URL1 + ++p;
            OWLIndividual eventIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
            System.out.println("URL" + ":" +URL);
            eventMap.put(URL, eventIndividual);
//            Set set = personMap.entrySet();
//            Iterator iterator = set.iterator();
//            Map.Entry personentry = (Map.Entry) iterator.next();
          //  System.out.println("Event Key is: " + personentry.getKey() + " & " + " value is: " + personentry.getValue());
            System.out.println("Person Key is: "+(eventMap.get(URL)).toString());
            OWLClassAssertionAxiom personAsserion = factory.getOWLClassAssertionAxiom(EventC, eventIndividual);
            manager.addAxiom(ontology, personAsserion);
           
            
            // THE problem is here for axiom assertion , previously it was the same (even with random or with out randon method, when we want to assert this section we need to assert value which is person and event individual to the participate in , but here the person.get(personIndividual ) is in another loop so can not be added here_
            
            OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, personMap.get(personIndividual),eventMap.get( eventIndividual));
          // OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, personMap.get(URL),eventMap.get(URL));
             }
    }
}



//
//           
//             
//             for (int i = 0; i < 5; i++) {
//             String URL = BASE_URL1 + (Math.abs(r.nextInt(10)));
//             OWLIndividual moveIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
//             Map<String, OWLIndividual> moveMap = new HashMap<>();
//             moveMap.put(URL, moveIndividual);
//             Set set = moveMap.entrySet();
//             Iterator iterator = set.iterator();
////             while (iterator.hasNext()) {
////             Map.Entry entry = (Map.Entry) iterator.next();
////             System.out.println("Move Key is: " + entry.getKey() + " & " + " value is: " + entry.getValue());
//             //            for (String key : moveMap.keySet()) {
//             //                System.out.println("Move Key is: " + key + " & " + " value is: "
//             //                        + moveMap.get(key));
//             OWLClassAssertionAxiom movetAsserion = factory.getOWLClassAssertionAxiom(MoveC, moveIndividual);
//             manager.addAxiom(ontology, movetAsserion);
//
//             }
//             
//              // Random r3 = newRandom();
//             for (int i = 0; i < 5; i++) {
//             String URL = BASE_URL1 + (Math.abs(r.nextInt(1000)));
//             OWLIndividual kickIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
//             Map< String, OWLIndividual> kickMap = new HashMap<>();
//             kickMap.put(URL, kickIndividual);
//             Set set = kickMap.entrySet();
//             Iterator iterator = set.iterator();
////             while (iterator.hasNext()) {
////             Map.Entry entry = (Map.Entry) iterator.next();
////             System.out.println("Kick Key is: " + entry.getKey() + " & " + " value is: " + entry.getValue());
//             //            for (String key : kickMap.keySet()) {
//             //                System.out.println("kick Key is: " + key + " & " + " value is: " + kickMap.get(key));
//             OWLClassAssertionAxiom kickAsserion = factory.getOWLClassAssertionAxiom(KickC, kickIndividual);
//             manager.addAxiom(ontology, kickAsserion);
//             }
//             
//
//             // Random r3 = new Random(); //
//             for (int i = 0; i < 5; i++) {
//             String URL = BASE_URL1 + (Math.abs(r.nextInt(20)));
//             OWLIndividual pushIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
////             Map< String, OWLIndividual> pushMap = new HashMap<>();
//             pushMap.put(URL, pushIndividual);
//             Set set = pushMap.entrySet();
////             Iterator iterator = set.iterator();
////             while (iterator.hasNext()) {
////             Map.Entry entry = (Map.Entry) iterator.next();
////             System.out.println("Push Key is: " + entry.getKey() + " & " + " value is: " + entry.getValue());
//             //            for (String key : pushMap.keySet()) {
//             //                System.out.println("Push Key is: " + key + " & " + " value is: "
//             //                        + pushMap.get(key));
//             OWLClassAssertionAxiom pushAsserion
//             = factory.getOWLClassAssertionAxiom(PushC, pushIndividual);
//             manager.addAxiom(ontology, pushAsserion);
//             }
//             
//    
//             File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_M_saved.owl");
//             manager.saveOntology(ontology, IRI.create(ontologySave.toURI()));
//             } 
//}
//
//
//
//           /* Map<String, OWLIndividual>
//             * vehicleMap = new HashMap<>(); //Random r = new Random(); for (int i = 0; i <
//             * 5; i++) 
//{ String URL = BASE_URL1 + (Math.abs(r.nextInt(50))); OWLIndividual
//             * vehicleIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
//             * vehicleMap.put(URL, vehicleIndividual); for (String key :
//             * vehicleMap.keySet()) { System.out.println("Vehicke Key is: " + key + " & " +
//             * " value is: " + vehicleMap.get(key)); OWLClassAssertionAxiom vehicleAsserion
//             * = factory.getOWLClassAssertionAxiom(CarC, vehicleIndividual);
//             * manager.addAxiom(ontology, vehicleAsserion); }
//             *
//           
//          
//             * ///**  
//             * // _________________________________________________________________________________

//}
// Use Map<OWLClass, Integer> - to create a list of total individuals needed to instantiate the class.
// Iterate through the Key - Value pair of Map.
// Inside the loop, get the total value of Key (numner of individuals for each OWLClass
// create a second for loop and instantiate above number of OWLIndividuals
// Create separate assertions for each individual against OWL Class
// add these assertions to the ontology repository.
