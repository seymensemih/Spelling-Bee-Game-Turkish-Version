package midtermproject.visualmidterm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Random;

public class Controller  {
    @FXML
    protected Button letter1;
    @FXML
    protected Button letter2;
    @FXML
    protected Button letter3;
    @FXML
    protected Button letter4;
    @FXML
    protected Button letter5;
    @FXML
    protected Button letter6;
    @FXML
    protected Button centerletter;
    @FXML
    protected Button delButton;
    @FXML
    protected Button shuffleButton;
    @FXML
    protected Button okButton;
    @FXML
    protected Button randStart;
    @FXML
    protected Button manStart;
    @FXML
    protected TextField txtStart;
    @FXML
    protected TextField txtAnswer;
    @FXML
    protected TextArea txtArea;
    @FXML
    protected  Label outputLabel;
    @FXML
    protected Label skorLabel;
    @FXML
    protected Label pointLabel;
    @FXML
    protected Label bulunanLabel;
    @FXML
    protected Label txtTebrik;
    String alfabe="abcçdefgğhıijklmnoöprsştuüvyz";

    @FXML
    private void onLetter1Click(){
        txtAnswer.appendText(letter1.getText().toLowerCase());
    }
    @FXML
    private void onLetter2Click(){
        txtAnswer.appendText(letter2.getText().toLowerCase());
    }
    @FXML
    private void onLetter3Click(){
        txtAnswer.appendText(letter3.getText().toLowerCase());
    }
    @FXML
    private void onLetter4Click(){
        txtAnswer.appendText(letter4.getText().toLowerCase());
    }
    @FXML
    private void onLetter5Click(){
        txtAnswer.appendText(letter5.getText().toLowerCase().toLowerCase());
    }
    @FXML
    private void onLetter6Click(){
        txtAnswer.appendText(letter6.getText().toLowerCase());
    }
    @FXML
    private void onCenterClick(){
        txtAnswer.appendText(centerletter.getText().toLowerCase());
    }
    @FXML
    private void onDelButtonClick(){
        if(txtAnswer.getText().isEmpty()){
            outputLabel.setText("Silinecek bir şey yok.");
        }
        else {
            String text = txtAnswer.getText();
            txtAnswer.setText(text.substring(0, text.length() - 1));
            outputLabel.setText("");
        }
    }
    @FXML
    private void onCevaplarClick(){
        if(Game.validWords==null)
        {
            outputLabel.setText("Oyun Başlamadı.");
        }
        else
        {   int sayac=1;
            txtArea.setText(sayac+"-");
            for(int i = 0; i < Game.validWords.size(); i++)
            {   sayac++;
                if(Game.wordsUsed.contains(Game.validWords.get(i)))
                {
                    txtArea.appendText("* ");
                }
                if(Game.validWords.get(i).length()>=4) {
                    txtArea.appendText(Game.validWords.get(i));
                    if(Game.wordsUsed.contains(Game.validWords.get(i)))
                    {
                        txtArea.appendText("- BULUNDU *");
                    }
                    if(Game.checkIfPangram(Game.validWords.get(i)))
                    {
                        txtArea.appendText(" - PANGRAM");
                    }
                    txtArea.appendText("\n"+sayac+"-");
                }
            }
            disableButtons();
        }
    }
    @FXML
    private void onShuffleButtonClick(){
        if( Game.displayedLetters==null )
        {
            outputLabel.setText("Karıştıracak Harf Yok");
        }
        else
        {
            Game.shuffle();
            displayLetters();
            outputLabel.setText("");
        }
    }

    @FXML
    protected void onCevapKontrolClick(){

        if((Game.pangrams.isEmpty()))
        {
                outputLabel.setText("Oyun daha başlamadı");
        }
        else
        {
            if(txtAnswer.getText().isEmpty())
            {
              outputLabel.setText("Bir cevap yazmadınız");
            }
            else
            {
                boolean uygun = true;

                for (int i=0; i<txtAnswer.getText().length();i++)
                {
                    if(Game.letters.indexOf(txtAnswer.getText().charAt(i))==-1)
                    {   uygun=false;
                        outputLabel.setText("Verilmeyen bir karakter kullandınız.");
                    }
                    else if (txtAnswer.getText().length() <= 3)
                    {
                        uygun=false;
                        outputLabel.setText("Girdiğiniz Kelime Çok Kısa");
                    }
                    else if (txtAnswer.getText().indexOf(Game.displayedLetters[0]) < 0)
                    {
                        uygun=false;
                        outputLabel.setText("Girdiğiniz kelime merkez harfi içermiyor.");
                    }
                    else if (!Game.validWords.contains(txtAnswer.getText()))
                    {
                        uygun=false;
                        outputLabel.setText("Girdiğiniz kelime sözlükte yok.");
                    }
                    else if (Game.wordsUsed.contains(txtAnswer.getText()))
                    {
                        uygun=false;
                        outputLabel.setText("Bu kelimeyi daha önce buldunuz.");
                    }
                }
                if(uygun)
                {
                    if(Game.points>=444 || Game.wordsUsed.size()>=Game.validWords.size()-1)
                    {
                        Game.wordsUsed.add(txtAnswer.getText());
                        txtArea.appendText(Game.wordsUsed.get(Game.wordsUsed.size()-1)+"\n");
                        disableButtons();
                        bulunanLabel.setText("Bulunan Kelimeler-->"+Game.wordsUsed.size()+"/" +Game.validWords.size());
                        txtTebrik.setText(Game.tebrik);
                        outputLabel.setText("Oyun Bitti Tebrikler.");
                    }
                    else
                    {
                        Game.updatePoints(txtAnswer.getText());
                        Game.wordsUsed.add(txtAnswer.getText());
                        txtArea.appendText(Game.wordsUsed.get(Game.wordsUsed.size()-1)+"\n");
                        txtAnswer.clear();
                        outputLabel.setText("");
                        bulunanLabel.setText("Bulunan Kelimeler-->"+Game.wordsUsed.size()+"/" +Game.validWords.size());
                        pointLabel.setText(String.valueOf(Game.points));
                        txtTebrik.setText(Game.tebrik);
                    }
                }
            }
        }
    }
    @FXML
    private void onManStartClick() throws IOException {

        if (txtStart.getText().isEmpty())
        {
            outputLabel.setText("Boş dizi girilemez.Lütfen kurallara uygun dizi giriniz.");
        }
        else
        {
            boolean uygunluk=true;
            if(txtStart.getText().length()!=7)
            {
                uygunluk=false;
                outputLabel.setText("7 harfli dizi girmediniz.Tekrar deneyin.");
            }
            else if (txtStart.getText().length()==7)
            {
                for (int i = 0; i < txtStart.getText().length(); i++)
                {
                    if (alfabe.indexOf(txtStart.getText().charAt(i)) == -1) {
                        uygunluk=false;
                        outputLabel.setText("Yanlış karakter girdiniz.Lütfen alfabede olan harflerden giriniz.");
                    }
                }
                for (int i = 0; i < txtStart.getText().length(); i++)
                {
                    char indexUsed = txtStart.getText().charAt(i);
                    for (int j = i + 1; j < txtStart.getText().length(); j++)
                    {
                        if (indexUsed == txtStart.getText().charAt(j))
                        {
                            uygunluk = false;
                            outputLabel.setText("Dizi aynı harften 1'den fazla kullanılmış.Tekrar Deneyin");
                        }
                    }
                }
            }
            if(uygunluk)
            {
                boolean start=true;
                new Game(txtStart.getText());
                Game.addValidWords();
                if(Game.pangrams.size()==0)
                {
                    start=false;
                    outputLabel.setText("Bu dizi ile pangram yok.Tekrar deneyiniz.");
                }
                if(start)
                {
                    if (Game.validWords==null | Game.wordsUsed==null)
                    {

                        new Game(txtStart.getText());
                        Game.addValidWords();
                        displayLetters();
                        enableButtons();
                        txtArea.clear();
                        outputLabel.setText("Yeni oyun başladı.\nPangram sayısı-->"+Game.pangrams.size()+"\n Kelime Sayısı-->"+Game.validWords.size());
                    }
                    else
                    {
                        clearGame();
                        new Game(txtStart.getText());
                        Game.addValidWords();
                        displayLetters();
                        enableButtons();
                        outputLabel.setText("Yeni oyun başladı.\nPangram sayısı-->"+Game.pangrams.size()+"\n Kelime Sayısı-->"+Game.validWords.size());
                    }
                }
            }
        }
    }

    @FXML
    private void onOtoGameStartClick() throws IOException {

        if (Game.validWords==null | Game.pangrams==null | Game.wordsUsed==null)
        {
            otoGame();
            Game.addValidWords();
            displayLetters();
            enableButtons();
            txtArea.clear();
            outputLabel.setText("Yeni oyun başladı.\nPangram sayısı-->"+Game.pangrams.size()+"\n Kelime Sayısı-->"+Game.validWords.size());
        }
        else {
            clearGame();
            otoGame();
            Game.addValidWords();
            displayLetters();
            enableButtons();
            outputLabel.setText("Yeni oyun başladı.\nPangram sayısı-->"+Game.pangrams.size()+"\n Kelime Sayısı-->"+Game.validWords.size());
        }
    }
    public void otoGame() throws IOException
    {   Game.allPlayableWords();
        Random random=new Random();
        int index=random.nextInt(Game.possibleGames.size());
        new Game(Game.possibleGames.get(index));
    }

    public void displayLetters(){
        letter1.setText(String.valueOf(Game.displayedLetters[1]));
        letter2.setText(String.valueOf(Game.displayedLetters[2]));
        letter3.setText(String.valueOf(Game.displayedLetters[3]));
        letter4.setText(String.valueOf(Game.displayedLetters[4]));
        letter5.setText(String.valueOf(Game.displayedLetters[5]));
        letter6.setText(String.valueOf(Game.displayedLetters[6]));
        centerletter.setText(String.valueOf(Game.displayedLetters[0]));
    }
    private void enableButtons()
    {
        txtAnswer.setDisable(false);
        letter1.setDisable(false);
        letter2.setDisable(false);
        letter3.setDisable(false);
        letter4.setDisable(false);
        letter5.setDisable(false);
        letter6.setDisable(false);
        centerletter.setDisable(false);
        okButton.setDisable(false);
        shuffleButton.setDisable(false);
        delButton.setDisable(false);

    }
    private void disableButtons()
    {
        outputLabel.setText("Oyun Bitti Skorunuz-->"+Game.points+"\nBulunan kelime-->"+Game.wordsUsed.size());
        txtAnswer.setDisable(true);
        letter1.setDisable(true);
        letter2.setDisable(true);
        letter3.setDisable(true);
        letter4.setDisable(true);
        letter5.setDisable(true);
        letter6.setDisable(true);
        delButton.setDisable(true);
        okButton.setDisable(true);
        shuffleButton.setDisable(true);
        centerletter.setDisable(true);
    }

    private void clearGame()
    {
        Game.validWords.clear();
        Game.pangrams.clear();
        Game.wordsUsed.clear();
        Game.points=0;
        bulunanLabel.setText("");
        txtArea.clear();
        txtAnswer.clear();
        pointLabel.setText("");
        txtTebrik.setText("");
    }

}
