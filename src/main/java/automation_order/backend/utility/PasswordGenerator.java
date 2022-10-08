package automation_order.backend.utility;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordGenerator {

    public String createPassword(){
        int length = 10;
        String[] charCategories = new String[]{
                "abcdefghijklmnopqrstuvwxyz",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                "0123456789"
        };
        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        for (int i = 0; i < length; i++) {
            String charCategory = charCategories[random.nextInt(charCategories.length)];
            int position = random.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }

        return new String(password);
    }

}
