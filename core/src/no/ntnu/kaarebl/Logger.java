public class LoggerSingleton {
        private static LoggerSingleton instance = null;
	protected LoggerSingleton(){
	}
	public static LoggerSingleton getInstance(){
	        if(instance == null){
	                instance = new LoggerSingleton();
	        }
	        return instance;
        }
}
