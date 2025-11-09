import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.Arrays;

public class InstituteMongoDBOperations {

    private static final String DATABASE_NAME = "InstituteDB";
    private static final String COLLECTION_NAME = "Students";

    public static void main(String[] args) {

        // Connect to MongoDB
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            // 1. Create Database (or get existing)
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Database '" + DATABASE_NAME + "' accessed/created successfully.");

            // 2. Create Collection (or get existing)
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            System.out.println("Collection '" + COLLECTION_NAME + "' accessed/created successfully.");

            // 3. Insert Documents
            Document student1 = new Document("name", "Alice")
                    .append("age", 20)
                    .append("major", "Computer Science")
                    .append("courses", Arrays.asList("Data Structures", "Algorithms"));

            collection.insertOne(student1);
            System.out.println("Inserted document: " + student1.toJson());

            Document student2 = new Document("name", "Bob")
                    .append("age", 22)
                    .append("major", "Electrical Engineering")
                    .append("courses", Arrays.asList("Circuits", "Signals"));

            collection.insertOne(student2);
            System.out.println("Inserted document: " + student2.toJson());

            // 4. Display All Documents
            System.out.println("\nDisplaying all students:");
            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

            // 5. Update Document (Alice's age)
            collection.updateOne(Filters.eq("name", "Alice"), Updates.set("age", 21));
            System.out.println("\nUpdated Alice's age.");

            // Display After Update
            System.out.println("\nDisplaying all students after update:");
            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

            // 6. Remove Document (Bob)
            collection.deleteOne(Filters.eq("name", "Bob"));
            System.out.println("\nRemoved Bob.");

            // Display After Removal
            System.out.println("\nDisplaying all students after removal:");
            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


// Step 1:

// Download this jar (direct official link):
// https://repo1.maven.org/maven2/org/mongodb/mongodb-driver-sync/4.11.1/mongodb-driver-sync-4.11.1.jar

// Also download BSON jar:
// https://repo1.maven.org/maven2/org/mongodb/bson/4.11.1/bson-4.11.1.jar

// Also download Core driver:
// https://repo1.maven.org/maven2/org/mongodb/mongodb-driver-core/4.11.1/mongodb-driver-core-4.11.1.jar


// Step 2:
// Save the jars in the same directory as the Java file.

// Step 3:
// Compile the Java file:
// javac -cp ".:mongodb-driver-sync-4.11.1.jar:mongodb-driver-core-4.11.1.jar:bson-4.11.1.jar" InstituteMongoDBOperations.java

// Step 4:
// Run the Java file:
// java -cp ".:mongodb-driver-sync-4.11.1.jar:mongodb-driver-core-4.11.1.jar:bson-4.11.1.jar" InstituteMongoDBOperations
// (base) trushantramdasjadhav@Trushants-MacBook-Air DBMS-Code % java -cp ".:mongodb-driver-sync-4.11.1.jar:mongodb-driver-core-4.11.1.jar:bson-4.11.1.jar" InstituteMongoDBOperations

// Nov 09, 2025 12:49:29 PM com.mongodb.internal.diagnostics.logging.Loggers shouldUseSLF4J
// WARNING: SLF4J not found on the classpath.  Logging is disabled for the 'org.mongodb.driver' component
// Database 'InstituteDB' accessed/created successfully.
// Collection 'Students' accessed/created successfully.
// Inserted document: {"name": "Alice", "age": 20, "major": "Computer Science", "courses": ["Data Structures", "Algorithms"], "_id": {"$oid": "69104081a37205552ac39c34"}}
// Inserted document: {"name": "Bob", "age": 22, "major": "Electrical Engineering", "courses": ["Circuits", "Signals"], "_id": {"$oid": "69104081a37205552ac39c35"}}

// Displaying all students:
// {"_id": {"$oid": "69104081a37205552ac39c34"}, "name": "Alice", "age": 20, "major": "Computer Science", "courses": ["Data Structures", "Algorithms"]}
// {"_id": {"$oid": "69104081a37205552ac39c35"}, "name": "Bob", "age": 22, "major": "Electrical Engineering", "courses": ["Circuits", "Signals"]}

// Updated Alice's age.

// Displaying all students after update:
// {"_id": {"$oid": "69104081a37205552ac39c34"}, "name": "Alice", "age": 21, "major": "Computer Science", "courses": ["Data Structures", "Algorithms"]}
// {"_id": {"$oid": "69104081a37205552ac39c35"}, "name": "Bob", "age": 22, "major": "Electrical Engineering", "courses": ["Circuits", "Signals"]}

// Removed Bob.

// Displaying all students after removal:
// {"_id": {"$oid": "69104081a37205552ac39c34"}, "name": "Alice", "age": 21, "major": "Computer Science", "courses": ["Data Structures", "Algorithms"]}
// (base) trushantramdasjadhav@Trushants-MacBook-Air DBMS-Code % 