using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Models
{
    public class MeerkeuzeVraag
    {
        #region Properties
        public int Id { get; set; }
        public int QuestionId { get; set; }
        public string Vraag { get; set; }
        public string Keuze1 { get; set; }
        public string Keuze2 { get; set; }
        public string Keuze3 { get; set; }
        public string Antwoord { get; set; }

        #endregion

        #region Constructors
        public MeerkeuzeVraag(int questionId,string vraag, string keuze1, string keuze2, string keuze3, string antwoord)
        {
            QuestionId = questionId;
            Vraag = vraag;
            Keuze1 = keuze1;
            Keuze2 = keuze2;
            Keuze3 = keuze3;
            Antwoord = antwoord;
        }
        #endregion
    }
}
