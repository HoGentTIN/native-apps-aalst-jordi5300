using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Models
{
    public class Speler
    {
        #region Properties
        public int Id { get; set; }

        public string NickName { get; set; }

        public string Email { get; set; }

        public int Score { get; set; }

        public int Tijd { get; set; }

        #endregion

        #region Constructors
        public Speler()
        {
        }
        #endregion
    }
}

