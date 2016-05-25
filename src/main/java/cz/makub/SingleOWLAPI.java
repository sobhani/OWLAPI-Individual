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
public class SingleOWLAPI{

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
        OWLObjectProperty hasPart = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "hasPart"));

         Random r = new Random();
        OWLIndividual CarA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual Alex = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual Martin = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual BreakingA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual DamageA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual ArmA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual KickA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual PushA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual RunA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual ThrowA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual WindowsA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        OWLIndividual MoveFA = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r.nextInt(100)))));
        
        
       
        
        OWLClassAssertionAxiom axiom7 = factory.getOWLClassAssertionAxiom(KickC, KickA);
        manager.addAxiom(ontology, axiom7);
        OWLClassAssertionAxiom axiom9 = factory.getOWLClassAssertionAxiom(MoveC, MoveFA);
        manager.addAxiom(ontology, axiom7);
        OWLClassAssertionAxiom axiom11 = factory.getOWLClassAssertionAxiom(FastMove, MoveFA);
        manager.addAxiom(ontology, axiom11);
        OWLClassAssertionAxiom axiom12 = factory.getOWLClassAssertionAxiom(EventC, MoveFA);
        manager.addAxiom(ontology, axiom12);
        //OWLClassAssertionAxiom axiom13 = factory.getOWLClassAssertionAxiom(MoveSlow, MoveSA);
       // manager.addAxiom(ontology, axiom11);
        //OWLClassAssertionAxiom axiom14 = factory.getOWLClassAssertionAxiom(MoveC, MoveSA);
       // manager.addAxiom(ontology, axiom14);
        OWLClassAssertionAxiom axiom15 = factory.getOWLClassAssertionAxiom(PushC, PushA);
        manager.addAxiom(ontology, axiom11);
        OWLClassAssertionAxiom axiom17 = factory.getOWLClassAssertionAxiom(RunC, RunA);
        manager.addAxiom(ontology, axiom11);
        OWLClassAssertionAxiom axiom19 = factory.getOWLClassAssertionAxiom(ThrowC, RunA);
        manager.addAxiom(ontology, axiom11);
        OWLClassAssertionAxiom axioma = factory.getOWLClassAssertionAxiom(ArmC, ArmA);
        manager.addAxiom(ontology, axioma);
        OWLClassAssertionAxiom axiomb = factory.getOWLClassAssertionAxiom(CarC, CarA);
        manager.addAxiom(ontology, axiomb);
        OWLClassAssertionAxiom axiomc = factory.getOWLClassAssertionAxiom(PersonC, Alex);
        manager.addAxiom(ontology, axiomc);
        OWLClassAssertionAxiom axiomd = factory.getOWLClassAssertionAxiom(PersonC, Martin);
        manager.addAxiom(ontology, axiomd);
        OWLClassAssertionAxiom axiome = factory.getOWLClassAssertionAxiom(WindowsC, WindowsA);
        manager.addAxiom(ontology, axiome);
        
        
   
        OWLObjectPropertyAssertionAxiom axiom23 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, ArmA, MoveFA);
        manager.addAxiom(ontology, axiom23);
        OWLObjectPropertyAssertionAxiom axiom24 = factory.getOWLObjectPropertyAssertionAxiom(partOf, ArmA, Alex);
        manager.addAxiom(ontology, axiom24);
        OWLObjectPropertyAssertionAxiom axiom25 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, Alex, RunA);
        manager.addAxiom(ontology, axiom25);
        OWLObjectPropertyAssertionAxiom axiom26 = factory.getOWLObjectPropertyAssertionAxiom(participant, BreakingA, WindowsA);
        manager.addAxiom(ontology, axiom26);
        OWLObjectPropertyAssertionAxiom axiom27 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, CarA, WindowsA);
        manager.addAxiom(ontology, axiom27);
        OWLObjectPropertyAssertionAxiom axiom28 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, DamageA, PushA);
        manager.addAxiom(ontology, axiom28);
        OWLObjectPropertyAssertionAxiom axiom29 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, DamageA, BreakingA);
        manager.addAxiom(ontology, axiom29);
        OWLObjectPropertyAssertionAxiom axiom30 = factory.getOWLObjectPropertyAssertionAxiom(hasPart, DamageA, KickA);
        manager.addAxiom(ontology, axiom30);
        OWLObjectPropertyAssertionAxiom axiom31 = factory.getOWLObjectPropertyAssertionAxiom(partOf, DamageA, ThrowA);
        manager.addAxiom(ontology, axiom31);
        OWLObjectPropertyAssertionAxiom axiom34 = factory.getOWLObjectPropertyAssertionAxiom(participant, KickA, CarA);
        manager.addAxiom(ontology, axiom34);
        OWLObjectPropertyAssertionAxiom axiom35 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, Martin, PushA);
        manager.addAxiom(ontology, axiom35);
        OWLObjectPropertyAssertionAxiom axiom36 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, Martin, BreakingA);
        manager.addAxiom(ontology, axiom36);
        OWLObjectPropertyAssertionAxiom axiom37 = factory.getOWLObjectPropertyAssertionAxiom(participateIn, Martin, KickA);
        manager.addAxiom(ontology, axiom37);
        OWLObjectPropertyAssertionAxiom axiom38 = factory.getOWLObjectPropertyAssertionAxiom(participant, CarA, PushA);
        manager.addAxiom(ontology, axiom38);
        OWLObjectPropertyAssertionAxiom axiom39 = factory.getOWLObjectPropertyAssertionAxiom(participant, RunA, Alex);
        manager.addAxiom(ontology, axiom39);
        
        File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_savedSingelOwwl.owl");
                manager.saveOntology(ontology, IRI.create(ontologySave.toURI()));  

    }

}