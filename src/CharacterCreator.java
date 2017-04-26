import javax.swing.SwingUtilities;

public class CharacterCreator {
	
	static double thisVersion = 0;

	public static void main(String[] args) {
		try {
			if (Double.parseDouble(UpdateChecker.getLatestVersion()) > thisVersion) {
				new UpdateInfo(UpdateChecker.getWhatsNew());
			} else {
				// Initial Data List Setup work:
				@SuppressWarnings("unused")
				GenerateSourceData sourceData = new GenerateSourceData();

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new MainFrame();
					}
				});
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}