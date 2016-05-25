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
import static jdk.nashorn.internal.runtime.PropertyMap.newMap;
import static jdk.nashorn.internal.runtime.PropertyMap.newMap;
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
public class FHashMapowlapi {

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

        //Random random = new Random();
        //for (int i = 0; i < 5; i++) {
//       OWLIndividual CarIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Random.anyRandomIntRange(random,1, 50))));
//       OWLClassAssertionAxiom carAsserion = factory.getOWLClassAssertionAxiom(CarC, CarIndividual);
//       manager.addAxiom(ontology, carAsserion);
        OWLObjectProperty participateIn = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "participateIn"));
        OWLObjectProperty partOf = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "partOf"));
        OWLObjectProperty participant = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "participant"));
        OWLObjectProperty hasPart = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "hasPart"));

        // HashMap< String, OWLIndividual> verify = new HashMap<>();
        Random r = new Random();

        Map<OWLClass, Integer> countIndividual = new HashMap<>();

        //countIndividual.put(PersonC, r.nextInt(10)); //this is important
        for (int j = 0; j < 2; j++) {
            countIndividual.put(PersonC, j);

            for (OWLClass key : countIndividual.keySet()) {
                //System.out.println("PERSONkeyClass: " + key + " " + "ValueClass:" + countIndividual.get(key));
                Map< Integer, OWLIndividual> verify = new HashMap<>();
                OWLIndividual individual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + r.nextInt(100)));
                //System.out.println("pERSONIndividual" + individual);
                verify.put(countIndividual.get(key), individual);
               for (Integer keyC : verify.keySet()) {
                System.out.println("PERSONkeyCCCCClass: " + keyC + " " + "ValueClass:" + verify.get(keyC));
                OWLClassAssertionAxiom assertion = factory.getOWLClassAssertionAxiom(key, individual);
                manager.addAxiom(ontology, assertion);
                // OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, verify.getValue(key), verify.getValue(IRI));
                //OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, verify.get(keyC)),verify.get();
//                File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_saved.owl");
//                manager.saveOntology(ontology, IRI.create(ontologySave.toURI()));
               

        
             //countIndividual.put(PersonC, r.nextInt(10)); //this is important
        
            countIndividual.put(RunC, j);
         
             for (OWLClass keyR : countIndividual.keySet()) {
               System.out.println("RUNkeyClass: " + keyR + " " + "ValueClass:" + countIndividual.get(keyR));
                Map< Integer, OWLIndividual> Runverify = new HashMap<>();
                OWLIndividual Runindividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + r.nextInt(100)));
                //System.out.println("RUNIndividual" + individual);
               Runverify.put(countIndividual.get(keyR), Runindividual);
               for (Integer keyRun : Runverify.keySet()) {
               System.out.println("RunkeyCCCCClass: " + keyRun + " " + "ValueClass:" + Runverify.get(keyRun));
                OWLClassAssertionAxiom assertionRun = factory.getOWLClassAssertionAxiom(key, individual);
                manager.addAxiom(ontology, assertion);
                // OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, verify.getValue(key), verify.getValue(IRI));
                //OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, verify.get(keyC)),verify.get();
                File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_saved.owl");
                manager.saveOntology(ontology, IRI.create(ontologySave.toURI()));   
            

       OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, verify.get(keyC), verify.get(keyRun));
        manager.addAxiom(ontology, axiomAssertion);
               }
             }
               }
            }
        }
    }
}
  

//        countIndividual.put(CarC, r.nextInt(10));
//        countIndividual.put(WindowsC, r.nextInt(2));
//        countIndividual.put(ArmC, r.nextInt(2));
//        countIndividual.put(ObjectC, r.nextInt(2));
//        countIndividual.put(ThrowC, r.nextInt(2));
//        countIndividual.put(RunC, r.nextInt(2));
//        countIndividual.put(PushC, r.nextInt(2));
/*  //This is very important :/
 for (OWLClass key : countIndividual.keySet()) {
            
 System.out.println("keyClass: " + key + " " + "ValueClass:" + countIndividual.get(key));

 HashMap< Integer, OWLIndividual> verify = new HashMap<>();
            
 for (int i = 0; i < countIndividual.get(key); i++) {
 //OWLIndividual individual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + r.nextInt(500)));
 OWLIndividual individual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + i));
 System.out.println("Individual: " +individual);
                
 verify.put(countIndividual.get(key), individual);
 System.out.println("keyIndivisual" + countIndividual.get(key)+ "" +"ValueIndividual"+ individual);
 // System.out.println("key" + key + "" + "Value" + countIndividual.get(key));
 OWLClassAssertionAxiom assertion = factory.getOWLClassAssertionAxiom(key, individual);
 manager.addAxiom(ontology, assertion);
 }
 }*/
//        for (int i = 0; i <= 3; i++) {
//            String URL = BASE_URL1 + (Math.abs(r.nextInt(100)));
//            OWLIndividual personIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
//            verify.put(URL, personIndividual);
//            for (String key : verify.keySet()) {
//                System.out.print(key + ":" + verify.get(key));
//            }
//            OWLClassAssertionAxiom personAsserion = factory.getOWLClassAssertionAxiom(PersonC, personIndividual);
//            manager.addAxiom(ontology, personAsserion);
// HashMap< String ,OWLIndividual> verify = new HashMap<>();
//Random r = new Random();
//for (int i = 0; i <= 3; i++) {
//OWLIndividual personIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
// verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), personIndividual);
//Hash Map iteration
//            for (String key:verify.keySet())
//           System.out.print(key + ":" + verify.get(key));
// String key = BASE_URL1 + (Math.abs(r.nextInt(100))) ; 
//OWLIndividual value =verify.get(key);
//System.out.println("key" + key +"value"+value);
//             OWLClassAssertionAxiom personAsserion = factory.getOWLClassAssertionAxiom(PersonC, personIndividual);
//             manager.addAxiom(ontology, personAsserion);
//System.out.println("the string is "+personIndividual);
// System.out.println("the baseURL is "+BASE_URL1 + (Math.abs(r.nextInt(100))));
//  System.out.println("the assertion is "+personAsserion);
//        for (int i = 0; i < 2; i++) {
//            OWLIndividual CarIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(200)))));
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), CarIndividual);
//            OWLClassAssertionAxiom carAsserion = factory.getOWLClassAssertionAxiom(CarC, CarIndividual);
//            manager.addAxiom(ontology, carAsserion);
//            for (String key : verify.keySet()) {
//                System.out.print(key + ":" + verify.get(key));
//            }
//             String key = BASE_URL1;
//           OWLIndividual value =verify.get(key);
//           System.out.println("key" + key +"value"+value);
//  System.out.println("the string is " + CarIndividual);
// OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, verify.get(i),verify.get(i));
// OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, verify.getValue("http://www.eecs.qmul.ac.uk/~sobhani/Test/Throw2.owl#1"), verify.getValue("http://www.eecs.qmul.ac.uk/~sobhani/Test/Throw2.owl#26"));
// }
//        for (int i = 0; i <= 2; i++) {
//            OWLIndividual moveIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(200)))));
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), moveIndividual);
//            OWLClassAssertionAxiom moveAsserion = factory.getOWLClassAssertionAxiom(MoveC, moveIndividual);
//            manager.addAxiom(ontology, moveAsserion);
//OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, verify.getValue(IRI), verify.getValue(IRI));
//        }
//
//        for (int counter = 1; counter <= 2; counter++) {
//            OWLIndividual breakingIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(300)))));
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), breakingIndividual);
//            OWLClassAssertionAxiom breakingAsserion = factory.getOWLClassAssertionAxiom(BreakingC, breakingIndividual);
//            manager.addAxiom(ontology, breakingAsserion);
//        }
//        for (int counter = 1; counter <= 2; counter++) {
//            OWLIndividual damageIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(400)))));
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), damageIndividual);
//            OWLClassAssertionAxiom damageAsserion = factory.getOWLClassAssertionAxiom(DamageC, damageIndividual);
//            manager.addAxiom(ontology, damageAsserion);
//        }
//
//        for (int counter = 1; counter <= 2; counter++) {
//            OWLIndividual illegalTIvidual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(500)))));
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), illegalTIvidual);
//            OWLClassAssertionAxiom illegalTAsserion = factory.getOWLClassAssertionAxiom(IllegalTC, illegalTIvidual);
//            manager.addAxiom(ontology, illegalTAsserion);
//        }
//
//        for (int counter = 1; counter <= 2; counter++) {
//            OWLIndividual ArmIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(600)))));
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), ArmIndividual);
//            OWLClassAssertionAxiom ArmAsserion = factory.getOWLClassAssertionAxiom(ArmC, ArmIndividual);
//            manager.addAxiom(ontology, ArmAsserion);
//        }
//        for (int counter = 1; counter <= 2; counter++) {
//            OWLIndividual throwIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(700)))));
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), throwIndividual);
//            OWLClassAssertionAxiom throwAsserion = factory.getOWLClassAssertionAxiom(ThrowC, throwIndividual);
//            manager.addAxiom(ontology, throwAsserion);
//        }
//
//        for (int counter = 1; counter <= 2; counter++) {
//            OWLIndividual moveFAIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(800)))));
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), moveFAIndividual);
//            OWLClassAssertionAxiom moveFAAsserion = factory.getOWLClassAssertionAxiom(FastMove, moveFAIndividual);
//            manager.addAxiom(ontology, moveFAAsserion);
//        }
//
//        for (int counter = 1; counter <= 2; counter++) {
//            OWLIndividual runIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(900)))));
//            OWLClassAssertionAxiom runAsserion = factory.getOWLClassAssertionAxiom(RunC, runIndividual);
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), runIndividual);
//            manager.addAxiom(ontology, runAsserion);
//        }
//
//        for (int counter = 1; counter <= 2; counter++) {
//            OWLIndividual kickIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(1000)))));
//            OWLClassAssertionAxiom kickAsserion = factory.getOWLClassAssertionAxiom(KickC, kickIndividual);
//            verify.put(BASE_URL1 + (Math.abs(r.nextInt(100))), kickIndividual);
//            manager.addAxiom(ontology, kickAsserion);
//        }
//        
//        OWLObjectPropertyAssertionAxiom axiomAsserion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, personIndividual, runIndividual);
//        manager.addAxiom(ontology, axiomAsserion);
//        
//____________________________________________________________________
//        OWLIndividual CarA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual Alex = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual Martin = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual BreakingA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual DamageA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual ArmA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual IllegalTA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual KickA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual PushA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual RunA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual ThrowA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual WindowsA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual MoveA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual MoveFA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLIndividual MoveSA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
//        OWLClass cls = manager.getOWLDataFactory().getOWLClass(IRI.create(BASE_URL1 + pm));
//        OWLClassAssertionAxiom axiom3 = factory.getOWLClassAssertionAxiom(DamageC, DamageA);
//       manager.addAxiom(ontology, axiom3);
//        OWLClassAssertionAxiom axiom5 = factory.getOWLClassAssertionAxiom(IllegalTC, IllegalTA);
//        manager.addAxiom(ontology, axiom5);
//        OWLClassAssertionAxiom axiom7 = factory.getOWLClassAssertionAxiom(KickC, KickA);
//        manager.addAxiom(ontology, axiom7);
//        OWLClassAssertionAxiom axiom9 = factory.getOWLClassAssertionAxiom(MoveC, MoveA);
//        manager.addAxiom(ontology, axiom7);
//        OWLClassAssertionAxiom axiom11 = factory.getOWLClassAssertionAxiom(FastMove, MoveFA);
//        manager.addAxiom(ontology, axiom11);
//        OWLClassAssertionAxiom axiom12 = factory.getOWLClassAssertionAxiom(EventC, MoveFA);
//        manager.addAxiom(ontology, axiom12);
//        OWLClassAssertionAxiom axiom13 = factory.getOWLClassAssertionAxiom(MoveSlow, MoveSA);
//        manager.addAxiom(ontology, axiom11);
//        OWLClassAssertionAxiom axiom14 = factory.getOWLClassAssertionAxiom(MoveC, MoveSA);
//        manager.addAxiom(ontology, axiom14);
//        OWLClassAssertionAxiom axiom15 = factory.getOWLClassAssertionAxiom(PushC, PushA);
//        manager.addAxiom(ontology, axiom11);
//        OWLClassAssertionAxiom axiom17 = factory.getOWLClassAssertionAxiom(RunC, RunA);
//        manager.addAxiom(ontology, axiom11);
//        OWLClassAssertionAxiom axiom19 = factory.getOWLClassAssertionAxiom(ThrowC, RunA);
//        manager.addAxiom(ontology, axiom11);
//        OWLClassAssertionAxiom axioma = factory.getOWLClassAssertionAxiom(ArmC, ArmA);
//        manager.addAxiom(ontology, axioma);
//        OWLClassAssertionAxiom axiomb = factory.getOWLClassAssertionAxiom(CarC, CarA);
//        manager.addAxiom(ontology, axiomb);
//        OWLClassAssertionAxiom axiomc = factory.getOWLClassAssertionAxiom(PersonC, Alex);
//        manager.addAxiom(ontology, axiomc);
//        OWLClassAssertionAxiom axiomd = factory.getOWLClassAssertionAxiom(PersonC, Martin);
//        manager.addAxiom(ontology, axiomd);
//        OWLClassAssertionAxiom axiome = factory.getOWLClassAssertionAxiom(WindowsC, WindowsA);
//        manager.addAxiom(ontology, axiome);
//System.out.println("print the " + participateIn);
//        OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, personIndividual, moveFAIndividual);
//        manager.addAxiom(ontology, axiomAssertion);      
//        OWLObjectPropertyAssertionAxiom axiomAsserion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, personIndividual, runIndividual);
//        manager.addAxiom(ontology, axiomAsserion);
//        OWLObjectPropertyAssertionAxiom axiom23 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, ArmA, MoveFA);
//        manager.addAxiom(ontology, axiom23);
//        OWLObjectPropertyAssertionAxiom axiom24 = factory.getOWLObjectPropertyAssertionAxiom(partOf, ArmA, Alex);
//        manager.addAxiom(ontology, axiom24);
//        OWLObjectPropertyAssertionAxiom axiom25 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, Alex, RunA);
//        manager.addAxiom(ontology, axiom25);
//        OWLObjectPropertyAssertionAxiom axiom26 = factory.getOWLObjectPropertyAssertionAxiom(participant, BreakingA, WindowsA);
//        manager.addAxiom(ontology, axiom26);
//        OWLObjectPropertyAssertionAxiom axiom27 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, CarA, WindowsA);
//        manager.addAxiom(ontology, axiom27);
//        OWLObjectPropertyAssertionAxiom axiom28 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, DamageA, PushA);
//        manager.addAxiom(ontology, axiom28);
//        OWLObjectPropertyAssertionAxiom axiom29 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, DamageA, BreakingA);
//        manager.addAxiom(ontology, axiom29);
//        OWLObjectPropertyAssertionAxiom axiom30 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, DamageA, KickA);
//        manager.addAxiom(ontology, axiom30);
//        OWLObjectPropertyAssertionAxiom axiom31 = factory.getOWLObjectPropertyAssertionAxiom(partOf, DamageA, ThrowA);
//        manager.addAxiom(ontology, axiom31);
//        OWLObjectPropertyAssertionAxiom axiom32 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, IllegalTA, RunA);
//        manager.addAxiom(ontology, axiom32);
//        OWLObjectPropertyAssertionAxiom axiom33 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, IllegalTA, ThrowA);
//        manager.addAxiom(ontology, axiom33);
//        OWLObjectPropertyAssertionAxiom axiom34 = factory.getOWLObjectPropertyAssertionAxiom(participant, KickA, CarA);
//        manager.addAxiom(ontology, axiom34);
//        OWLObjectPropertyAssertionAxiom axiom35 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, Martin, PushA);
//        manager.addAxiom(ontology, axiom35);
//        OWLObjectPropertyAssertionAxiom axiom36 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, Martin, BreakingA);
//        manager.addAxiom(ontology, axiom36);
//        OWLObjectPropertyAssertionAxiom axiom37 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, Martin, KickA);
//        manager.addAxiom(ontology, axiom37);
//        OWLObjectPropertyAssertionAxiom axiom38 = factory.getOWLObjectPropertyAssertionAxiom(participant, CarA, PushA);
//        manager.addAxiom(ontology, axiom38);
//        OWLObjectPropertyAssertionAxiom axiom39 = factory.getOWLObjectPropertyAssertionAxiom(participant, RunA, Alex);
//        manager.addAxiom(ontology, axiom39);
//        OWLObjectPropertyAssertionAxiom axiom340 = factory.getOWLObjectPropertyAssertionAxiom(partOf, ThrowA, IllegalTA);
//        manager.addAxiom(ontology, axiom340);
// _________________________________________________________________________________
//        File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_saved.owl");
//        manager.saveOntology(ontology, IRI.create(ontologySave.toURI()));
//    }
//}
////        //get class and its individuals
//        OWLClass personClass = factory.getOWLClass(":Person", pm);
//
//       for (OWLNamedIndividual person : reasoner.getInstances(personClass, false).getFlattened()) {
//            System.out.println(" : person " + renderer.render(person ));
//       }
//       
//             OWLClass objectClass = factory.getOWLClass(":Object", pm);
//
//       for (OWLNamedIndividual object : reasoner.getInstances(objectClass, false).getFlattened()) {
//            System.out.println(" : object " + renderer.render(object));  
//        }
//       
//       
//        OWLClass EventClass = factory.getOWLClass(":Event", pm);
//
//       for (OWLNamedIndividual event : reasoner.getInstances(EventClass, false).getFlattened()) {
//            System.out.println(" : event " + renderer.render(event));  
//        }
//        
//    
//     //get a given individual
//        OWLNamedIndividual Alex = factory.getOWLNamedIndividual(":Alex", pm);
//          //OWLNamedIndividual car= factory.getOWLNamedIndividual(":Car", pm);
//          // OWLNamedIndividual Martin= factory.getOWLNamedIndividual(":Martin", pm);
////        //get values of selected properties on the individual
////        OWLDataProperty hasEmailProperty = factory.getOWLDataProperty(":hasEmail", pm);
//       OWLObjectProperty participateInproperty= factory.getOWLObjectProperty(":participateIn", pm);
//        
//      // for (OWLLiteral email : reasoner.getDataPropertyValues(martin, hasEmailProperty)) {
////            System.out.println("Martin has email: " + email.getLiteral());
////        }
////
//     for (OWLNamedIndividual ind : reasoner.getObjectPropertyValues(Alex, participateInproperty).getFlattened()) {
//            System.out.println("Alex is participateIn : " + renderer.render(ind)); }
//     
//      OWLNamedIndividual car= factory.getOWLNamedIndividual(":Car", pm);      
//     for (OWLNamedIndividual ind1 : reasoner.getObjectPropertyValues(car, participateInproperty).getFlattened()) {
//            System.out.println("Car is participateIn : " + renderer.render(ind1));}
//     
//            OWLNamedIndividual Martin= factory.getOWLNamedIndividual(":Martin", pm);
//            for (OWLNamedIndividual ind2 : reasoner.getObjectPropertyValues(car, participateInproperty).getFlattened()) {
//            System.out.println("Martin is participateIn : " + renderer.render(ind2));
//            }  
//            
//            OWLNamedIndividual Windows= factory.getOWLNamedIndividual(":Windows", pm);
//            for (OWLNamedIndividual ind3 : reasoner.getObjectPropertyValues(Windows, participateInproperty).getFlattened()) {
//            System.out.println("Windows is participateIn : " + renderer.render(ind3)); }
//            
//            OWLObjectProperty participantproperty= factory.getOWLObjectProperty(":participant", pm);
//            OWLNamedIndividual It= factory.getOWLNamedIndividual(":It", pm);
//            for (OWLNamedIndividual ind4 : reasoner.getObjectPropertyValues(It,participantproperty).getFlattened()) {
//            System.out.println("It has participant : " + renderer.render(ind4)); 
//     }   
//    
//  
////        //get labels
////        LocalizedAnnotationSelector as = new LocalizedAnnotationSelector(ontology, factory, "en", "cs");
////        for (OWLNamedIndividual ind : reasoner.getObjectPropertyValues(martin, isEmployedAtProperty).getFlattened()) {
////            System.out.println("Martin is employed at: '" + as.getLabel(ind) + "'");
////        }
////
////        //get inverse of a property, i.e. which individuals are in relation with a given individual
//        OWLNamedIndividual event = factory.getOWLNamedIndividual(":Throw", pm);
//        OWLObjectPropertyExpression inverse = factory.getOWLObjectInverseOf(participateInproperty);
//      for (OWLNamedIndividual ind : reasoner.getObjectPropertyValues(event, inverse).getFlattened()) {
//            System.out.println("Throw inverseOf(participateAt) -> " + renderer.render(ind));
//       }
//      
//       //find to which classes the individual belongs
//       Set<OWLClassExpression> assertedClasses = Martin.getTypes(ontology);
//       for (OWLClass c : reasoner.getTypes(Martin, false).getFlattened()) {
//            boolean asserted = assertedClasses.contains(c);
//           System.out.println((asserted ? "asserted" : "inferred") + " class for Martin: " + renderer.render(c));
//              }
////
//        Set<OWLClassExpression> assertedClasses1 = Alex.getTypes(ontology);
//       for (OWLClass C : reasoner.getTypes(Alex, false).getFlattened()) {
//            boolean asserted = assertedClasses1.contains(C);
//           System.out.println((asserted ? "asserted" : "inferred") + " class for ALEX: " + renderer.render(C));
//            }
//
//
//        //list all object property values for the individual
//        Map<OWLObjectPropertyExpression, Set<OWLIndividual>> assertedValues = Martin.getObjectPropertyValues(ontology);
//        for (OWLObjectProperty objProp : ontology.getObjectPropertiesInSignature(true)) {
//            for (OWLNamedIndividual ind : reasoner.getObjectPropertyValues(Martin, objProp).getFlattened()) {
//                boolean asserted = assertedValues.get(objProp).contains(ind);
//                System.out.println((asserted ? "asserted" : "inferred") + " object property for Martin: "
//                        + renderer.render(objProp) + " -> " + renderer.render(ind));
//            }
//        }
//    
//       Map<OWLObjectPropertyExpression, Set<OWLIndividual>> assertedValues2 = Alex.getObjectPropertyValues(ontology);
//        for (OWLObjectProperty objProp2 : ontology.getObjectPropertiesInSignature(true)) {
//            for (OWLNamedIndividual ind2 : reasoner.getObjectPropertyValues(Alex, objProp2).getFlattened()) {
//                boolean asserted = assertedValues2.get(objProp2).contains(ind2);
//                System.out.println((asserted ? "asserted" : "inferred") + " object property forAlex: "
//                        + renderer.render(objProp2) + " -> " + renderer.render(ind2));
//        //list all same individuals
//        for (OWLNamedIndividual ind : reasoner.getSameIndividuals(martin)) {
//            System.out.println("same as Martin: " + renderer.render(ind));
//        }
////
////        //ask reasoner whether Martin is employed at MU
//        boolean result = reasoner.isEntailed(factory.getOWLObjectPropertyAssertionAxiom(participateInproperty, Alex, event));
//       System.out.println("Is participateIn event ? : " + result);
//    
//
//check whether the SWRL rule is used
//        OWLNamedIndividual alex = factory.getOWLNamedIndividual(":Alex", pm);
//        OWLClass Throw = factory.getOWLClass(":Run", pm);
//        OWLClassAssertionAxiom axiomToExplain = factory.getOWLClassAssertionAxiom(Throw, alex);
//        System.out.println("Is alex doing Throwing ? : " + reasoner.isEntailed(axiomToExplain));
//
//        //explain why Ivan is child of married parents
//        DefaultExplanationGenerator explanationGenerator =
//                new DefaultExplanationGenerator(
//                        manager, reasonerFactory, ontology, reasoner, new SilentExplanationProgressMonitor());
//        Set<OWLAxiom> explanation = explanationGenerator.getExplanation(axiomToExplain);
//        ExplanationOrderer deo = new ExplanationOrdererImpl(manager);
//        ExplanationTree explanationTree = deo.getOrderedExplanation(axiomToExplain, explanation);
//        System.out.println();
//        System.out.println("-- explanation why Ivan is in class ChildOfMarriedParents --");
//        printIndented(explanationTree, "");
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    
//    }
//
//    private static void printIndented(Tree<OWLAxiom> node, String indent) {
//        OWLAxiom axiom = node.getUserObject();
//        System.out.println(indent + renderer.render(axiom));
//        if (!node.isLeaf()) {
//            for (Tree<OWLAxiom> child : node.getChildren()) {
//                printIndented(child, indent + "    ");
//            }
//        }
//    }
//
//    /**
//     * Helper class for extracting labels, comments and other anotations in preffered languages.
//     * Selects the first literal annotation matching the given languages in the given order.
//     */
//    public static class LocalizedAnnotationSelector {
//        private final List<String> langs;
//        private final OWLOntology ontology;
//        private final OWLDataFactory factory;
//
//        /**
//         * Constructor.
//         *
//         * @param ontology ontology
//         * @param factory  data factory
//         * @param langs    list of prefered languages; if none is provided the Locale.getDefault() is used
//         */
//        public LocalizedAnnotationSelector(OWLOntology ontology, OWLDataFactory factory, String... langs) {
//            this.langs = (langs == null) ? Arrays.asList(Locale.getDefault().toString()) : Arrays.asList(langs);
//            this.ontology = ontology;
//            this.factory = factory;
//        }
//
//        /**
//         * Provides the first label in the first matching language.
//         *
//         * @param ind individual
//         * @return label in one of preferred languages or null if not available
//         */
//        public String getLabel(OWLNamedIndividual ind) {
//            return getAnnotationString(ind, OWLRDFVocabulary.RDFS_LABEL.getIRI());
//        }
//
//        @SuppressWarnings("UnusedDeclaration")
//        public String getComment(OWLNamedIndividual ind) {
//            return getAnnotationString(ind, OWLRDFVocabulary.RDFS_COMMENT.getIRI());
//        }
//
//        public String getAnnotationString(OWLNamedIndividual ind, IRI annotationIRI) {
//            return getLocalizedString(ind.getAnnotations(ontology, factory.getOWLAnnotationProperty(annotationIRI)));
//        }
//
//        private String getLocalizedString(Set<OWLAnnotation> annotations) {
//            List<OWLLiteral> literalLabels = new ArrayList<OWLLiteral>(annotations.size());
//            for (OWLAnnotation label : annotations) {
//                if (label.getValue() instanceof OWLLiteral) {
//                    literalLabels.add((OWLLiteral) label.getValue());
//                }
//            }
//            for (String lang : langs) {
//                for (OWLLiteral literal : literalLabels) {
//                    if (literal.hasLang(lang)) return literal.getLiteral();
//                }
//            }
//            for (OWLLiteral literal : literalLabels) {
//                if (!literal.hasLang()) return literal.getLiteral();
//            }
//            return null;
//        }
//    }
//}
// Use Map<OWLClass, Integer> - to create a list of total individuals needed to instantiate the class.
// Iterate through the Key - Value pair of Map.
// Inside the loop, get the total value of Key (numner of individuals for each OWLClass
// create a second for loop and instantiate above number of OWLIndividuals
// Create separate assertions for each individual against OWL Class
        // add these assertions to the ontology repository.
