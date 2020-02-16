using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Banque.Client.View
{
    class RestHelper
    {
        public string sendRequest(String baseUrl, String endpoint, String method, byte[] data)
        {
            //byte[] data = Encoding.ASCII.GetBytes($"username=user&password=password");
            var responseContent = "";
            try
            {
                var request = WebRequest.Create(baseUrl + endpoint);
                request.Method = method;
                request.ContentType = "application/x-www-form-urlencoded";
                request.ContentLength = data.Length;

                if (!"GET".Equals(method))
                using (Stream stream = request.GetRequestStream())
                {
                    stream.Write(data, 0, data.Length);
                }               

                using (WebResponse response = request.GetResponse())
                {
                    using (Stream stream = response.GetResponseStream())
                    {
                        using (var sr99 = new StreamReader(stream))
                        {
                            responseContent = sr99.ReadToEnd();
                        }
                    }
                }
            }
            catch (WebException e)
            {
                Console.WriteLine("Couldn't connect to Server " + e.Data.Values.ToString());
            }
            return responseContent;
        }
    }
}
