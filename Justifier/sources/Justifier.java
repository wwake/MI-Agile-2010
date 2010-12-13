import java.util.Vector;

public class Justifier {

	public static enum JoinType
	{
		AllLeft,
		AllRight,
		EndToEnd,
		None
	};
	
	private Library library;
	private Document result;
	private Vector<String> lines;
	private JoinType type = JoinType.None;
	
	public Justifier(Library theLibrary, Vector<String> theLines, JoinType howToJoin)
	{
		lines = theLines;
		type = howToJoin;
		this.resetResultDocument();
	}
	
	public void resetResultDocument()
	{
		result = new Document();
		result.setHowCreated(TextLine.LineType.AppCreated);
	}
	
	public Vector<String> selectedLines()
	{
		return lines;
	}
	
	public Library library()
	{
		return library;
	}
	
	public Document document()
	{
		return result;
	}
	
	public void execute()
	{
	    this.joinAllFrom(this.selectedLines(), this.document().settings().nextName());
	    this.library().input(this.document());
	}

	void joinAllFrom(Vector<String> linesToAlign, String defaultName)
	{
		nameResultContigUsing(sequencesToJoin, defaultName);
	
		// Remove the Selected sequences from the project
		GCUtilities::for_each(sequencesToJoin, boost::bind(&GCModel::Project::removeSequence, &m_ownerProject, _1, false, Project::kNotifyBeforeAndAfter));
	
		SequenceList::const_iterator itr = sequencesToJoin.begin();
		SequenceList::const_iterator endItr = sequencesToJoin.end();
		for (; itr != endItr; ++itr)
		{
			if (isFragment(*itr))
			{
				addFragment(*asFragment(*itr));
			}
			else if (isContig(*itr))
			{
				addContig(asContig(*itr));
			}
		}
	}

}
//void MindlesslyJoinBase::joinFragmentsFrom(Contig* sourceContig, int offsetAdjustment)
//{
//	SequenceList::const_iterator itr = sourceContig->sequences().begin();
//	SequenceList::const_iterator endItr = sourceContig->sequences().end();
//	for (; itr != endItr; ++itr)
//	{
//		(*itr)->disconnect();
//		Fragment* fragment = asFragment(*itr);
//		result()->adoptFragment(*fragment, offsetAdjustment + fragment->contigOffset(), false);
//	}
//	delete sourceContig;
//	sourceContig = NULL;
//}
//
//void MindlesslyJoinBase::alignTo5PrimeEnd(Contig* sourceContig)
//{
//    joinFragmentsFrom(sourceContig, 0);
//}
//
//void MindlesslyJoinBase::alignTo3PrimeEnd(Contig* sourceContig)
//{
//	int currentResultLength = m_resultContig->unCalculatedConsensusLength();
// 	int sourceLength = sourceContig->length();
//
//	int sourceFragmentAdjustmentForFinalLength = 0;
//	if (currentResultLength < sourceLength)
//    {
//	    adjustResultSequencesBy(sourceLength - currentResultLength);
//    }
//	else
//	{
//		sourceFragmentAdjustmentForFinalLength = currentResultLength - sourceLength;
//	}
//    joinFragmentsFrom(sourceContig, sourceFragmentAdjustmentForFinalLength);
//}
//
//void MindlesslyJoinBase::appendTo3PrimeEnd(Contig* sourceContig)
//{
//    int originalLength = result()->unCalculatedConsensusLength();
//    joinFragmentsFrom(sourceContig, originalLength);
//}
//
//void MindlesslyJoinBase::nameResultContigUsing(const GCModel::SequenceList& sequences, QString defaultName)
//{
//	std::set<QString, UserNamedCompare> userNames;
//	 // TODO: user pref for contig name pattern instead of hardwired Contig[*]
//	std::set<QString, GeneratedNameCompare> generatedNames(GeneratedNameCompare("Contig[*]"));
//	SequenceList::const_iterator sequenceItr = sequences.begin();
//	SequenceList::const_iterator sequenceEnd = sequences.end();
//	for (; sequenceItr != sequenceEnd; ++sequenceItr)
//	{
//		if (isContig(*sequenceItr))
//		{
//			Contig* inputContig = asContig(*sequenceItr);
//			if (inputContig->nameIsUserSpecified())
//			{
//				userNames.insert(inputContig->name());
//			}
//			else
//			{
//				// TODO: further distinguish between handle and assembly generated names?
//				generatedNames.insert(inputContig->name());
//			}
//		}
//	}
//	if (userNames.size() > 0)
//	{
//		m_resultContig->setName(*userNames.begin(), true);
//	}
//	else if (generatedNames.size() > 0)
//	{
//		m_resultContig->setName(*generatedNames.begin());
//	}
//	else
//	{
//		m_resultContig->setName(defaultName);
//	}
//}
//
//void MindlesslyJoinBase::joinAllFrom(const GCModel::SequenceList& sequencesToJoin, const QString& defaultName)
//{
//	nameResultContigUsing(sequencesToJoin, defaultName);
//
//	// Remove the Selected sequences from the project
//	GCUtilities::for_each(sequencesToJoin, boost::bind(&GCModel::Project::removeSequence, &m_ownerProject, _1, false, Project::kNotifyBeforeAndAfter));
//
//	SequenceList::const_iterator itr = sequencesToJoin.begin();
//	SequenceList::const_iterator endItr = sequencesToJoin.end();
//	for (; itr != endItr; ++itr)
//	{
//		if (isFragment(*itr))
//		{
//			addFragment(*asFragment(*itr));
//		}
//		else if (isContig(*itr))
//		{
//			addContig(asContig(*itr));
//		}
//	}
//}
//
//void MindlesslyJoinBase::addFragment(Fragment& fragment)
//{
//	int offset = 0;  // m_type == ALL_LEFT
//	int currentResultLength = result()->unCalculatedConsensusLength();
//	if (m_type == END_TO_END)
//	{
//		offset = currentResultLength;
//	}
//	else if (m_type == ALL_RIGHT)
//	{
//		if (currentResultLength >= fragment.length())
//		{
//			offset = currentResultLength - fragment.length();
//		}
//		else
//		{
//			adjustResultSequencesBy(fragment.length() - currentResultLength);
//		}			
//	}
//	result()->adoptFragment(fragment, offset, false);
//}
//
//void MindlesslyJoinBase::addContig(Contig* contig)
//{
//	if (m_type == ALL_LEFT)
//		this->alignTo5PrimeEnd(contig);
//	else if (m_type == ALL_RIGHT)
//		this->alignTo3PrimeEnd(contig);
//	else if (m_type == END_TO_END)
//		this->appendTo3PrimeEnd(contig);
//}
//
//void MindlesslyJoinBase::adjustResultSequencesBy(int offsetAdjustment)
//{
//	GCUtilities::for_each(
//		result()->sequences(), 
//		boost::bind(&GCModel::Sequence::adjustOffset, _1, offsetAdjustment, UpdatableItem::kNotify));
//}

