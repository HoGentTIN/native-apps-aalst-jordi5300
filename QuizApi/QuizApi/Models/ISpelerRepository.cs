using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Models
{
    public interface ISpelerRepository
    {
         Speler GetBy(string email);
            void Add(Speler speler);
            void SaveChanges();
    }
}
