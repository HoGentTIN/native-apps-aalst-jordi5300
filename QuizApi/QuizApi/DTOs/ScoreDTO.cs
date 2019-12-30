using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.DTOs
{
  public class ScoreDTO
  {
    public int Id { get; set; }
    public int quizId { get; set; }
    public string Nicknaam { get; set; }
    public int Punten { get; set; }
    public string Tijd { get; set; }
  }
}
