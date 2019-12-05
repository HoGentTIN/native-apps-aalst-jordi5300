using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Models
{
    public interface IQuizRepository
    {
        IEnumerable<Quiz> GetAll();
        Quiz GetbyId(int id);
        void Add(Quiz quiz);
        void Remove(Quiz quiz);
        void SaveChanges();
    bool TryGetQuiz(int id, out Quiz quiz);
  }
}
