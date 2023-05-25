package korchagin.model.enums;

public enum MusicGenre {
    CUTE_ROCK, RAP, JAZZ;

    public static MusicGenre getByName(String name) {
        return switch (name) {
            case "CUTE_ROCK" -> MusicGenre.CUTE_ROCK;
            case "RAP" -> MusicGenre.RAP;
            case "JAZZ" -> MusicGenre.JAZZ;
            default -> null;
        };
    }
}
