using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Models
{
  public class Score
  {
    #region Properties
    public int Id { get; set; }
    public int QuizId { get; set; }
    public string Nicknaam { get; set; }
    public int Punten { get; set; }
    public string Tijd { get; set; }
    #endregion

    #region Constructors
    public Score(int quizId, string nicknaam, int punten, string tijd)
    {
      QuizId = quizId;
      Nicknaam = nicknaam;
      Punten = punten;
      Tijd = tijd;
    }

    public Score()
    {
    }
    #endregion
  }
}
