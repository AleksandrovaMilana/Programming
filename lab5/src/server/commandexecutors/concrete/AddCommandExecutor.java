package server.commandexecutors.concrete;

import contract.dto.commanddto.concrete.AddCommandDTO;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commandexecutionresultdto.concrete.AddCommandExecutionResultDTO;
import contract.dto.commandexecutionresultdto.CommandExecutionResultDTO;
import server.CollectionManager;
import server.business.Coordinates;
import server.business.MusicBand;
import server.business.MusicGenre;
import server.business.Studio;
import server.commandexecutors.CommandExecutor;

import java.time.LocalDate;

public class AddCommandExecutor implements CommandExecutor {

    final private CollectionManager collectionManager;

    public AddCommandExecutor(CollectionManager collectionManager)
    {
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandExecutionResultDTO execute(CommandDTO commandDTO) {
        AddCommandDTO addCommandDTO = (AddCommandDTO) commandDTO;



        MusicBand musicBand = new MusicBand(
                this.collectionManager.generateId(),
                addCommandDTO.getMusicBandName(),
                new Coordinates(addCommandDTO.getMusicBandCoordinatesX(),
                        addCommandDTO.getMusicBandCoordinatesY()),
                LocalDate.now(),
                addCommandDTO.getMusicBandNumberOfParticipants(),
                addCommandDTO.getMusicBandSinglesCount(),
                MusicGenre.valueOf(addCommandDTO.getMusicBandMusicGenre()),
                new Studio(addCommandDTO.getMusicBandStudioName())
        );

       boolean flag = this.collectionManager.addMusicBandToTheCollection(musicBand);

       String commandExecutionResultMessage;

       if (flag)
           commandExecutionResultMessage = "Музыкальная группа успешно добавлена в коллекцию";
       else
           commandExecutionResultMessage = "Музыкальная группа не добавлена в коллекцию";
       return new AddCommandExecutionResultDTO(commandExecutionResultMessage);
    }
}
