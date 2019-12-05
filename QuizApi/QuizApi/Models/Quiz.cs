using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Models
{
    public class Quiz
    {
        #region Properties
        public int Id { get; set; }
        public string Naam { get; set; }
        public string Categorie { get; set; }
        #endregion

        #region Constructors
        public Quiz(string naam, string categorie)
        {
            Naam = naam;
            Categorie = categorie;
        }
        public Quiz()
        {
        }
        #endregion
    }
}