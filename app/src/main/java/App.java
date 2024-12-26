import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            DataHandler dataHandler = null;
            //「1」を選択した場合、CSVDataHandlerインスタンスを生成する
            //引数が0個のコンストラクタを実行するものとする
            if (choice.equals("1")) {
                dataHandler = new CSVDataHandler();

            } else if(choice.equals("2")) {
                //2」を選択した場合、JSONDataHandlerインスタンスを生成する
                dataHandler = new JSONDataHandler();
            } else {
                //不正な入力（「1」「2」以外）が与えられた場合、CSVDataHandlerインスタンスを生成する
                dataHandler = new CSVDataHandler();
            }

            //選択されたデータハンドラーをcom/recipeappパッケージのRecipeUIに渡し、displayMenuメソッドを呼び出す
            RecipeUI recipeUI = new RecipeUI(dataHandler);
            recipeUI.displayMenu();
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}