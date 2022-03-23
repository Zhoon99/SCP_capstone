// package kr.mmgg.scp.config;

// import java.io.FileInputStream;
// import java.io.IOException;

// import javax.annotation.PostConstruct;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import com.google.firebase.auth.FirebaseAuth;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.core.io.Resource;

// @Configuration
// public class FirebaseConfig {

//     private FirebaseApp firebaseApp;

//     public FirebaseApp initiallizeFCM() throws IOException {
//         // 리소스 폴더 안에 있는 파일 가져오기
//         // Resource resource = new ClassPathResource();
//         FileInputStream fis = new FileInputStream("scp/src/main/resources/firebaseprivatekey.json");
//         FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(fis))
//                 .build();
//         firebaseApp = FirebaseApp.initializeApp(options, "rudals");
//         return firebaseApp;
//     }

//     public FirebaseAuth getFirebaseAuth() throws IOException {
//         FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(initiallizeFCM());
//         return firebaseAuth;
//     }
// }
