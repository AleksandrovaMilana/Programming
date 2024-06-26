package client.commandDTOBuilder.cooncrete;

import client.commandDTOBuilder.CommandDTOBuilder;
import client.exeptions.InvalidCommandArgumentsInScriptFileException;
import client.io.ConsoleReader;
import client.io.ConsoleWriter;
import client.util.FieldValidators;
import client.util.MusicBandFieldGetter;
import client.util.StringIterator;
import contract.dto.commanddto.concrete.AddCommandDTO;
import contract.dto.commanddto.CommandDTO;
import contract.dto.commanddto.concrete.UpdateCommandDTO;

public class UpdateCommandDTOBuilder implements CommandDTOBuilder
{
    private MusicBandFieldGetter musicBandGetter;

    public UpdateCommandDTOBuilder(ConsoleReader consoleReader, ConsoleWriter consoleWriter)
    {
        this.musicBandGetter = new MusicBandFieldGetter(consoleReader, consoleWriter);
    }

    @Override
    public CommandDTO buildCommandDTO(String[] commandArguments)
    {
        long id;
        if (!FieldValidators.validateMusicBandId(commandArguments[0]))
            id = musicBandGetter.getMusicBandId();
        else
            id = Long.parseLong(commandArguments[0]);

        String musicBandName = musicBandGetter.getMusicBandName();
        Float musicBandCoordinatesX = musicBandGetter.getMusicBandCoordinatesX();
        Double musicBandCoordinatesY = musicBandGetter.getMusicBandCoordinatesY();
        Integer musicBandNumberOfParticipants = musicBandGetter.getMusicBandNumberOfParticipants();
        int musicBandSinglesCount = musicBandGetter.getMusicBandSinglesCount();
        String musicBandStudioName = musicBandGetter.getMusicBandStudioName();
        String musicBandMusicGenre = musicBandGetter.getMusicBandMusicGenre();

        return new UpdateCommandDTO(
                id,
                musicBandName,
                musicBandCoordinatesX,
                musicBandCoordinatesY,
                musicBandNumberOfParticipants,
                musicBandSinglesCount,
                musicBandStudioName,
                musicBandMusicGenre);
    }


    @Override
    public CommandDTO buildCommandDTOFromScript(String[] fileStrings, StringIterator stringIterator, String commandArgument) throws InvalidCommandArgumentsInScriptFileException {


        String musicBandNameString = fileStrings[stringIterator.increment()];
        String musicBandCoordinatesXString = fileStrings[stringIterator.increment()];
        String musicBandCoordinatesYString = fileStrings[stringIterator.increment()];
        String musicBandNumberOfParticipantsString = fileStrings[stringIterator.increment()];
        String musicBandSinglesCountString = fileStrings[stringIterator.increment()];
        String musicBandStudioNameString = fileStrings[stringIterator.increment()];
        String musicBandMusicGenreString = fileStrings[stringIterator.increment()];

        if (
                !FieldValidators.validateMusicBandId(commandArgument.trim()) ||
                !FieldValidators.validateMusicBandName(musicBandNameString) ||
                        !FieldValidators.validateMusicBandCoordinatesX(musicBandCoordinatesXString) ||
                        !FieldValidators.validateMusicBandCoordinatesY(musicBandCoordinatesYString) ||
                        !FieldValidators.validateMusicBandNumberOfParticipants(musicBandNumberOfParticipantsString) ||
                        !FieldValidators.validateMusicBandsSinglesCount(musicBandSinglesCountString) ||
                        !FieldValidators.validateMusicBandStudioName(musicBandStudioNameString) ||
                        !FieldValidators.validateMusicBandMusicGenre(musicBandMusicGenreString)

        ){
            System.out.println("При выполнении скрипта один из аргументов команды update не прошел валидацию. Скрипт не будет выполнен");
            throw new InvalidCommandArgumentsInScriptFileException();
        }

        long id = Long.parseLong(commandArgument.trim());

        String musicBandName = musicBandNameString;
        Float musicBandCoordinatesX = Float.parseFloat(musicBandCoordinatesXString);
        Double musicBandCoordinatesY = Double.parseDouble(musicBandCoordinatesYString);
        Integer musicBandNumberOfParticipants = Integer.parseInt(musicBandNumberOfParticipantsString);
        int musicBandSinglesCount = Integer.parseInt(musicBandSinglesCountString);
        String musicBandStudioName = musicBandStudioNameString;
        String musicBandMusicGenre = musicBandMusicGenreString;


        return new UpdateCommandDTO(
                id,
                musicBandName,
                musicBandCoordinatesX,
                musicBandCoordinatesY,
                musicBandNumberOfParticipants,
                musicBandSinglesCount,
                musicBandStudioName,
                musicBandMusicGenre);
    }



}
