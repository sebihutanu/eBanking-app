package org.poo.cb;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
            return;
        }

        String exchangeRatesCSV = "src/main/resources/" + args[0];
        String stockValuesCSV = "src/main/resources/" + args[1];
        String commands = "src/main/resources/" + args[2];

        CommandInvoker invoker = new CommandInvoker();
        Bank bank = Bank.getInstance();

        FileDataReaderFactory stockDataFactory = new StockDataFileDataReaderFactory();
        bank.readData(stockDataFactory, stockValuesCSV);
        FileDataReaderFactory exchangeRatesFactory = new ExchangeRatesFileDataReaderFactory();
        bank.readData(exchangeRatesFactory, exchangeRatesCSV);

        HashMap<String, User> users = bank.users;
        Map<String, List<Double>> stockDataMap = bank.stockDataMap;
        double[][] exchangeRatesMatrix = bank.exchangeRatesMatrix;

        File file = new File(commands);
        try{
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] token = line.split(" ");
                String commandName = token[0] + " " + token[1];
                Command command = null;
                switch(commandName) {
                    case "CREATE USER":
                        String email = token[2];
                        String firstName = token[3];
                        String lastName = token[4];
                        StringBuilder address = new StringBuilder();
                        for(int i = 5; i <= token.length - 1; i++) {
                            address.append(" ").append(token[i]);
                        }
                        address = new StringBuilder(address.substring(1));
                        command = new CreateUserCommand(email, firstName, lastName, address.toString(), users);
                        break;
                    case "ADD FRIEND":
                        String emailUser = token[2];
                        String emailFriend = token[3];
                        command = new AddFriendCommand(emailUser, emailFriend, users);
                        break;
                    case "ADD ACCOUNT":
                        String emailNewAccount = token[2];
                        String currency = token[3];
                        command = new AddAccountCommand(emailNewAccount, currency, users);
                        break;
                    case "ADD MONEY":
                        String emailAddMoney = token[2];
                        String currencyAddMoney = token[3];
                        double valueAddMoney = Double.parseDouble(token[4]);
                        command = new AddMoneyCommand(emailAddMoney, currencyAddMoney, valueAddMoney, users);
                        break;
                    case "EXCHANGE MONEY":
                        String emailExchangeMoney = token[2];
                        String sourceCurrency = token[3];
                        String destinationCurrency = token[4];
                        double amount = Double.parseDouble(token[5]);
                        command = new ExchangeMoneyCommand(emailExchangeMoney, sourceCurrency, destinationCurrency, amount, users, exchangeRatesMatrix);
                        break;
                    case "TRANSFER MONEY":
                        String emailTransferMoney = token[2];
                        String friendEmail = token[3];
                        String currencyTransfer = token[4];
                        double amountTransfer = Double.parseDouble(token[5]);
                        command = new TransferMoneyCommand(emailTransferMoney, friendEmail, currencyTransfer, amountTransfer, users);
                        break;
                    case "BUY STOCKS":
                        String emailBuyStocks = token[2];
                        String stockName = token[3];
                        int stockQuantity = Integer.parseInt(token[4]);
                        command = new BuyStocksCommand(emailBuyStocks, stockName, stockQuantity, stockDataMap, users);
                        break;
                    case "RECOMMEND STOCKS":
                        command = new RecommendStocksCommand(stockDataMap);
                        break;
                    case "LIST USER":
                        String emailListUser = token[2];
                        command = new ListUserCommand(emailListUser, users);
                        break;
                    case "LIST PORTFOLIO":
                        String emailListPortfolio = token[2];
                        command = new ListPortfolioCommand(emailListPortfolio, users);
                        break;
                    case "BUY PREMIUM":
                        String emailBuyPremium = token[2];
                        command = new BuyPremiumCommand(emailBuyPremium, users);
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
                if(command != null)
                    invoker.executeCommand(command);
            }
            bank.reset();
        } catch (FileNotFoundException e) {
            e.notify();
        }
    }
}

