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
        OWLObjectProperty hasPart = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "hasPart"));

        Random r = new Random();
//        Map<String, OWLIndividual> personMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            String URL = BASE_URL1 + (Math.abs(r.nextInt(500)));
            //System.out.println("UUURL" + ":" + URL);
            OWLIndividual personIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
            System.out.println("piii" + ":" + personIndividual);
            Map<String, OWLIndividual> personMap = new HashMap<>();
            personMap.put(URL, personIndividual);
            Set set = personMap.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                System.out.println("Person Key is: " + entry.getKey() + " & " + " value is: " + entry.getValue());
//                 for (Map.Entry entry: personMap.entrySet())
//                 System.out.println("Person Key is: " + entry.getKey()+ " & " + " value is: " + entry.getValue());
                // entry.getValue();
               OWLClassAssertionAxiom personAsserion = factory.getOWLClassAssertionAxiom(PersonC, personIndividual);
                manager.addAxiom(ontology, personAsserion);
//                File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_M_saved.owl");
//                manager.saveOntology(ontology, IRI.create(ontologySave.toURI()));

            }
        }
     
        for (int i = 0; i < 5; i++) {
            String URL = BASE_URL1 + (Math.abs(r.nextInt(100)));
            OWLIndividual eventIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
            Map<String, OWLIndividual> eventMap = new HashMap<>();
            eventMap.put(URL, eventIndividual);
//            for (String key : eventMap.keySet()) {
//                System.out.println("Event Key is: " + key + " & " + " value is: " + eventMap.get(key));
            Set set = eventMap.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                System.out.println("Event Key is: " + entry.getKey() + " & " + " value is: " + entry.getValue());
                OWLClassAssertionAxiom EventAsserion = factory.getOWLClassAssertionAxiom(EventC, eventIndividual);
                manager.addAxiom(ontology, EventAsserion); //OWLObjectPropertyAssertionAxiom
                //RunaxiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, eventMap.get(key), personMap.get(key1));
            }

        }
    }
}
        /*
        // Random r3 = new Random(); 
        for (int i = 0; i < 5; i++) {
            String URL = BASE_URL1 + (Math.abs(r.nextInt(10)));
            OWLIndividual moveIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
            Map<String, OWLIndividual> moveMap = new HashMap<>();
            moveMap.put(URL, moveIndividual);
            Set set = moveMap.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                System.out.println("Move Key is: " + entry.getKey() + " & " + " value is: " + entry.getValue());
//            for (String key : moveMap.keySet()) {
//                System.out.println("Move Key is: " + key + " & " + " value is: "
//                        + moveMap.get(key));
                OWLClassAssertionAxiom movetAsserion = factory.getOWLClassAssertionAxiom(MoveC, moveIndividual);
                manager.addAxiom(ontology, movetAsserion);

            }
        }
        ; // Random r3 = newRandom();
        for (int i = 0; i < 5; i++) {
            String URL = BASE_URL1 + (Math.abs(r.nextInt(1000)));
            OWLIndividual kickIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
            Map< String, OWLIndividual> kickMap = new HashMap<>();
            kickMap.put(URL, kickIndividual);
            Set set = kickMap.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                System.out.println("Kick Key is: " + entry.getKey() + " & " + " value is: " + entry.getValue());
//            for (String key : kickMap.keySet()) {
//                System.out.println("kick Key is: " + key + " & " + " value is: " + kickMap.get(key));
                OWLClassAssertionAxiom kickAsserion = factory.getOWLClassAssertionAxiom(KickC, kickIndividual);
                manager.addAxiom(ontology, kickAsserion);
            }
        }

        // Random r3 = new Random(); //
        for (int i = 0; i < 5; i++) {
            String URL = BASE_URL1 + (Math.abs(r.nextInt(20)));
            OWLIndividual pushIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
            Map< String, OWLIndividual> pushMap = new HashMap<>();
            pushMap.put(URL, pushIndividual);
            Set set = pushMap.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                System.out.println("Push Key is: " + entry.getKey() + " & " + " value is: " + entry.getValue());
//            for (String key : pushMap.keySet()) {
//                System.out.println("Push Key is: " + key + " & " + " value is: "
//                        + pushMap.get(key));
                OWLClassAssertionAxiom pushAsserion
                        = factory.getOWLClassAssertionAxiom(PushC, pushIndividual);
                manager.addAxiom(ontology, pushAsserion);
            }
        }
    
        File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_M_saved.owl");
                manager.saveOntology(ontology, IRI.create(ontologySave.toURI()));
    } 
}
    /* Map<String, OWLIndividual>
     * vehicleMap = new HashMap<>(); //Random r = new Random(); for (int i = 0; i <
     * 5; i++) { String URL = BASE_URL1 + (Math.abs(r.nextInt(50))); OWLIndividual
     * vehicleIndividual = factory.getOWLNamedIndividual(IRI.create(URL));
     * vehicleMap.put(URL, vehicleIndividual); for (String key :
     * vehicleMap.keySet()) { System.out.println("Vehicke Key is: " + key + " & " +
     * " value is: " + vehicleMap.get(key)); OWLClassAssertionAxiom vehicleAsserion
     * = factory.getOWLClassAssertionAxiom(CarC, vehicleIndividual);
     * manager.addAxiom(ontology, vehicleAsserion); }
     *
     * // Random r10 = new Random(); // for (int counter = 1;counter <= 5; counter  ++) {
     * //         OWLIndividual runIndividual = factory.getOWLNamedIndividual(IRI.create(BASE_URL1 + (Math.abs(r10.nextInt(900)))));
     * //        OWLClassAssertionAxiom runAsserion = factory.getOWLClassAssertionAxiom(RunC, runIndividual);
     * //        manager.addAxiom(ontology, runAsserion);
     * //    }
     * OWLObjectProperty participateIn = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "participateIn"));
     * OWLObjectProperty partOf = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "partOf"));
     * OWLObjectProperty participant = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "participant"));
     * OWLObjectProperty hasPart = factory.getOWLObjectProperty(IRI.create(BASE_URL1 + "hasPart"));
     *
     * File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_M_saved.owl");
     * manager.saveOntology(ontology, IRI.create(ontologySave.toURI()));
     *
     * //
     * }
     * }
     * }
     * //OWLObjectPropertyAssertionAxiom axiomAssertion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, personIndividual, moveFAIndividual);
     *
     * // OWLObjectPropertyAssertionAxiom axiomAsserion = factory.getOWLObjectPropertyAssertionAxiom(participateIn, personIndividual, runIndividual);
     * ///**  
     * // _________________________________________________________________________________
     * //    File ontologySave = new File("C:\\Users\\so_fa\\Desktop\\saveOwlapi\\Throw2_saved.owl");
     * //
     * //    manager.saveOntology (ontology, IRI.create
     * //
     * //(ontologySave.toURI()));
     * //    }
     * //}
     * ////        //get class and its individuals
     * //        OWLClass personClass = factory.getOWLClass(":Person", pm);
     * //
     * //       for (OWLNamedIndividual person : reasoner.getInstances(personClass, false).getFlattened()) {
     * //            System.out.println(" : person " + renderer.render(person ));
     * //       }
     * //
     * //             OWLClass objectClass = factory.getOWLClass(":Object", pm);
     * //
     * //       for (OWLNamedIndividual object : reasoner.getInstances(objectClass, false).getFlattened()) {
     * //            System.out.println(" : object " + renderer.render(object));
     * //        }
     * //
     * //
     * //        OWLClass EventClass = factory.getOWLClass(":Event", pm);
     * //
     * //       for (OWLNamedIndividual event : reasoner.getInstances(EventClass, false).getFlattened()) {
     * //            System.out.println(" : event " + renderer.render(event));
     * //        }
     * //
     * //
     * //     //get a given individual
     * //        OWLNamedIndividual Alex = factory.getOWLNamedIndividual(":Alex", pm);
     * //          //OWLNamedIndividual car= factory.getOWLNamedIndividual(":Car", pm);
     * //          // OWLNamedIndividual Martin= factory.getOWLNamedIndividual(":Martin", pm);
     * ////        //get values of selected properties on the individual
     * ////        OWLDataProperty hasEmailProperty = factory.getOWLDataProperty(":hasEmail", pm);
     * //       OWLObjectProperty participateInproperty= factory.getOWLObjectProperty(":participateIn", pm);
     * //
     * //      // for (OWLLiteral email : reasoner.getDataPropertyValues(martin, hasEmailProperty)) {
     * ////            System.out.println("Martin has email: " + email.getLiteral());
     * ////        }
     * ////
     * //     for (OWLNamedIndividual ind : reasoner.getObjectPropertyValues(Alex, participateInproperty).getFlattened()) {
     * //            System.out.println("Alex is participateIn : " + renderer.render(ind)); }
     * //
     * //      OWLNamedIndividual car= factory.getOWLNamedIndividual(":Car", pm);
     * //     for (OWLNamedIndividual ind1 : reasoner.getObjectPropertyValues(car, participateInproperty).getFlattened()) {
     * //            System.out.println("Car is participateIn : " + renderer.render(ind1));}
     * //
     * //            OWLNamedIndividual Martin= factory.getOWLNamedIndividual(":Martin", pm);
     * //            for (OWLNamedIndividual ind2 : reasoner.getObjectPropertyValues(car, participateInproperty).getFlattened()) {
     * //            System.out.println("Martin is participateIn : " + renderer.render(ind2));
     * //            }
     * //
     * //            OWLNamedIndividual Windows= factory.getOWLNamedIndividual(":Windows", pm);
     * //            for (OWLNamedIndividual ind3 : reasoner.getObjectPropertyValues(Windows, participateInproperty).getFlattened()) {
     * //            System.out.println("Windows is participateIn : " + renderer.render(ind3)); }
     * //
     * //            OWLObjectProperty participantproperty= factory.getOWLObjectProperty(":participant", pm);
     * //            OWLNamedIndividual It= factory.getOWLNamedIndividual(":It", pm);
     * //            for (OWLNamedIndividual ind4 : reasoner.getObjectPropertyValues(It,participantproperty).getFlattened()) {
     * //            System.out.println("It has participant : " + renderer.render(ind4));
     * //     }
     * //
     * //
     * ////        //get labels
     * ////        LocalizedAnnotationSelector as = new LocalizedAnnotationSelector(ontology, factory, "en", "cs");
     * ////        for (OWLNamedIndividual ind : reasoner.getObjectPropertyValues(martin, isEmployedAtProperty).getFlattened()) {
     * ////            System.out.println("Martin is employed at: '" + as.getLabel(ind) + "'");
     * ////        }
     * ////
     * ////        //get inverse of a property, i.e. which individuals are in relation with a given individual
     * //        OWLNamedIndividual event = factory.getOWLNamedIndividual(":Throw", pm);
     * //        OWLObjectPropertyExpression inverse = factory.getOWLObjectInverseOf(participateInproperty);
     * //      for (OWLNamedIndividual ind : reasoner.getObjectPropertyValues(event, inverse).getFlattened()) {
     * //            System.out.println("Throw inverseOf(participateAt) -> " +
     * renderer.render(ind)); // } // // //find to which classes the individual
     * belongs // Set<OWLClassExpression> assertedClasses =
     * Martin.getTypes(ontology); // for (OWLClass c : reasoner.getTypes(Martin,
     * false).getFlattened()) { // boolean asserted = assertedClasses.contains(c);
     * // System.out.println((asserted ? "asserted" : "inferred") + " class for
     * Martin: " + renderer.render(c)); // } //// // Set<OWLClassExpression>
     * assertedClasses1 = Alex.getTypes(ontology); // for (OWLClass C :
     * reasoner.getTypes(Alex, false).getFlattened()) { // boolean asserted =
     * assertedClasses1.contains(C); // System.out.println((asserted ? "asserted" :
     * "inferred") + " class for ALEX: " + renderer.render(C)); // } // // // //list
     * all object property values for the individual //
     * Map<OWLObjectPropertyExpression, Set<OWLIndividual>> assertedValues =
     * Martin.getObjectPropertyValues(ontology); // for (OWLObjectProperty objProp :
     * ontology.getObjectPropertiesInSignature(true)) { // for (OWLNamedIndividual
     * ind : reasoner.getObjectPropertyValues(Martin, objProp).getFlattened()) { //
     * boolean asserted = assertedValues.get(objProp).contains(ind); //
     * System.out.println((asserted ? "asserted" : "inferred") + " object property
     * for Martin: " // + renderer.render(objProp) + " -> " + renderer.render(ind));
     * // } // } // // Map<OWLObjectPropertyExpression, Set<OWLIndividual>>
     * assertedValues2 = Alex.getObjectPropertyValues(ontology); // for
     * (OWLObjectProperty objProp2 : ontology.getObjectPropertiesInSignature(true))
     * { // for (OWLNamedIndividual ind2 : reasoner.getObjectPropertyValues(Alex,
     * objProp2).getFlattened()) { // boolean asserted =
     * assertedValues2.get(objProp2).contains(ind2); // System.out.println((asserted
     * ? "asserted" : "inferred") + " object property forAlex: " // +
     * renderer.render(objProp2) + " -> " + renderer.render(ind2)); // //list all
     * same individuals // for (OWLNamedIndividual ind :
     * reasoner.getSameIndividuals(martin)) { // System.out.println("same as Martin:
     * " + renderer.render(ind)); // } //// //// //ask reasoner whether Martin is
     * employed at MU // boolean result =
     * reasoner.isEntailed(factory.getOWLObjectPropertyAssertionAxiom(participateInproperty,
     * Alex, event)); // System.out.println("Is participateIn event ? : " + result);
     * // // //check whether the SWRL rule is used // OWLNamedIndividual alex =
     * factory.getOWLNamedIndividual(":Alex", pm); // OWLClass Throw =
     * factory.getOWLClass(":Run", pm); // OWLClassAssertionAxiom axiomToExplain =
     * factory.getOWLClassAssertionAxiom(Throw, alex); // System.out.println("Is
     * alex doing Throwing ? : " + reasoner.isEntailed(axiomToExplain)); // //
     * //explain why Ivan is child of married parents // DefaultExplanationGenerator
     * explanationGenerator = // new DefaultExplanationGenerator( // manager,
     * reasonerFactory, ontology, reasoner, new SilentExplanationProgressMonitor());
     * // Set<OWLAxiom> explanation =
     * explanationGenerator.getExplanation(axiomToExplain); // ExplanationOrderer
     * deo = new ExplanationOrdererImpl(manager); // ExplanationTree explanationTree
     * = deo.getOrderedExplanation(axiomToExplain, explanation); //
     * System.out.println(); // System.out.println("-- explanation why Ivan is in
     * class ChildOfMarriedParents --"); // printIndented(explanationTree, ""); // }
     * // catch (Exception e) { // System.out.println(e.getMessage()); // } // // }
     * // // private static void printIndented(Tree<OWLAxiom> node, String indent) {
     * // OWLAxiom axiom = node.getUserObject(); // System.out.println(indent +
     * renderer.render(axiom)); // if (!node.isLeaf()) { // for (Tree<OWLAxiom>
     * child : node.getChildren()) { // printIndented(child, indent + " "); // } //
     * } // } // // /** // * Helper class for extracting labels, comments and other
     * anotations in preffered languages. // * Selects the first literal annotation
     * matching the given languages in the given order. //
     */
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
