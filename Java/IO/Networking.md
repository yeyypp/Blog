# Networking
- TCP
    - Socket
        
        - A socket is one endpoint of a two-way communication link between
        programs running on the network. A socket is bound to a port number
        so that the TCP layer can identify the application that the data
        is destined to be sent to.
        
        
        ```
        // server
        public static void initServer() {
        try (
                ServerSocket server = new ServerSocket(9000);
                Socket client = server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                ) {
            System.out.println("The server is working...");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (line.equals("end")) {
                    break;
                }
            }
            System.out.println("Server is closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        ```
        
        ```
        //client
        public static void initClient() {
        try (
                Socket client = new Socket("127.0.0.1", 9000);
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
                ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                if (userInput.equals("end")) {
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        }
        ```
        
