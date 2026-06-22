public class MediaAdapter implements MediaPlayer{
    private AdvancedMediaPlayer player;
    public MediaAdapter()
    {
        player = new AdvancedMediaPlayer();
    }

    @Override
    public void play(String audioType,String fileName)
    {
        if(audioType.equalsIgnoreCase("mp4"))
        {
            player.playMp4(fileName);
        }
    }
}
