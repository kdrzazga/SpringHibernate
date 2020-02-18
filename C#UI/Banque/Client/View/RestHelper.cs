using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Banque.Client.View
{
    class RestHelper
    {
        public string sendRequest(String baseUrl, String endpoint, HttpMethod method)
        { return sendRequest(baseUrl, endpoint, method, ""); }

        public string sendRequest(String baseUrl, String endpoint, HttpMethod method, string dollarString)
        {
            //dollarString = "$"username=user&password=password""
            byte[] data = Encoding.ASCII.GetBytes(dollarString);
            var responseContent = "";
            try
            {
                var request = WebRequest.Create(baseUrl + endpoint);
                request.Method = method.ToString();
                request.ContentType = "application/x-www-form-urlencoded";
                request.ContentLength = data.Length;

                if (data.Length > 0)
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
