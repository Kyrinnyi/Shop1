package ua.unif.commands;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public final class CommandFactory implements Commands{
    public static final Map<String, Command> map= new HashMap<String, Command>();
    
    static {
        map.put(AUTH, new AuthCommand1());
        map.put(BAD, new BadCommand());
        map.put(ORDERS, new OrdersCommand());
        map.put(SET_CLIENT_BLACK, new SetBlackCommand());
        map.put(NEW_GOOD, new NewGoodCommand());
        map.put(ADD_GOOD, new AddGoodCommand());
        map.put(GOOD, new GoodCommand());
        map.put(SET_AVAILABLE, new SetGoodAvailable());
        map.put(GOODS, new GoodsCommand());
        map.put(BUSKET, new AddToBusketCommand());
        map.put(DELETE, new DeleteFromBusketCommand());
        map.put(CONFIRM_ORDER, new ConfirmOrderCommand());
        map.put(ORDER_STATUS, new ChangeOrderStatusCommand());
        map.put(PAGE404, new Page404Command());
    }
    
    private CommandFactory (){      
    }
    
    public static Command getCommand(HttpServletRequest request){
        String command = request.getParameter(COM);
        Command c = map.get(command);
        if (c != null){
            return  c;
        } else {
            return map.get(PAGE404);
        }
    }
    
    public static Command getCommand(String name){
        Command c = map.get(name);
        if (c != null){
            return  c;
        } else {
            return map.get(BAD);
        }
    }
}
