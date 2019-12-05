using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Models
{
  public interface IScoreRepository
  {
    IEnumerable<Score> GetBy(int id);
    void Add(Score score);
    void SaveChanges();
  }
}
