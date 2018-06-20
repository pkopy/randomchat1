package pl.oskarpolak.randomchat.models.commands;

import org.springframework.web.socket.TextMessage;
import pl.oskarpolak.randomchat.models.UserModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SendPrivetMessage extends MainCommand {


    public SendPrivetMessage(List<UserModel> allUsers) {
        super(allUsers);
    }

    @Override
    public boolean executeCommand(UserModel sender, String... args) throws IOException {
        if(args.length < 2) {
            sender.sendMessage(new TextMessage("Podałeś za mało argumentów " + info()));
            return false;
        }

        Optional<UserModel> userToSend = findUserByNickname(args[0]);
        if(userToSend.isPresent()){
            userToSend.get().getUserSession().sendMessage(new TextMessage("PM:"+sender.getNickname()+":"+args[1]));
        }else{
            sender.sendMessage(new TextMessage("Taki nick nie istnieje!"));
        }
        return true;
    }

    @Override
    public String info() {
        return "Use send (userName) (message)";
    }
}
