using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Models
{
    public interface IMeerkeuzevraagRepository
    {
        IEnumerable<MeerkeuzeVraag> GetbyId(int quizid);
        void SaveChanges();
    }
}
